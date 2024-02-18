/**
 * Created by yangsong on 2014/11/25.
 * Socket类
 */
class Socket extends BaseClass {
    private _needReconnect:boolean = true;
    private _maxReconnectCount = 45;
    private _reconnectCount:number = 1;
    private _reconnectTime = 2000; // 毫秒

    private _reconnecting:boolean = false;     // 是否正在进行重连机制
    private _reconnectTimeout:boolean = false; // 重连超时
    private _startReconnect = false;    // 开始重连

    private _connectFlag:boolean;
    private _host:string;
    private _url:string;
    private _port:any;
    private _socket:egret.WebSocket;
    private _msg:BaseMsg;
    private _isConnecting:boolean;

    /**
     * 构造函数
     */
    public constructor() {
        super();
    }

    /**
     * 添加事件监听
     */
    private addEvents() {
        this._socket.addEventListener(egret.ProgressEvent.SOCKET_DATA, this.onReceiveMessage, this);
        this._socket.addEventListener(egret.Event.CONNECT, this.onSocketOpen, this);
        this._socket.addEventListener(egret.Event.CLOSE, this.onSocketClose, this);
        this._socket.addEventListener(egret.IOErrorEvent.IO_ERROR, this.onSocketError, this);
    }

    /**
     * 移除事件监听
     */
    private removeEvents():void {
        this._socket.removeEventListener(egret.ProgressEvent.SOCKET_DATA, this.onReceiveMessage, this);
        this._socket.removeEventListener(egret.Event.CONNECT, this.onSocketOpen, this);
        this._socket.removeEventListener(egret.Event.CLOSE, this.onSocketClose, this);
        this._socket.removeEventListener(egret.IOErrorEvent.IO_ERROR, this.onSocketError, this);
    }

    /**
     * 服务器连接成功
     */
    private onSocketOpen():void {
        this._reconnectCount = 1;
        this._reconnecting = false;
        this._reconnectTimeout = false;
        this._startReconnect = false;
        this._isConnecting = true;
        App.EasyLoading.hideLoading();
        if (this._connectFlag && this._needReconnect) {
            App.MessageCenter.dispatch(SocketConst.SOCKET_RECONNECT);
        } else {
            App.MessageCenter.dispatch(SocketConst.SOCKET_CONNECT);
        }

        this._connectFlag = true;
    }

    /**
     * 服务器断开连接
     */
    private onSocketClose():void {
        console.log('onSocketClose')
        this._isConnecting = false;
        if (this._needReconnect) {
            this.reconnect();
        } else {
            App.MessageCenter.dispatch(SocketConst.SOCKET_CLOSE);
        }
    }

    /**
     * 服务器连接错误
     */
    private onSocketError():void {
        console.log('onSocketError')
        if (this._needReconnect) {
            this.reconnect();
        } else {
            App.MessageCenter.dispatch(SocketConst.SOCKET_NOCONNECT);
        }
        this._isConnecting = false;
    }

    /**
     * 收到服务器消息
     * @param e
     */
    private onReceiveMessage(e:egret.Event):void {
        this._msg.receive(this._socket);
    }

    /**
     * 初始化服务区地址
     * @param host IP
     * @param port 端口
     * @param msg 消息发送接受处理类
     */
    public initServer(host:string, port:any, url:string, msg:BaseMsg):void {
        this._host = host;
        this._port = port;
        this._url = url;
        this._msg = msg;
    }

    /**
     * 开始Socket连接
     */
    public connect():void {
        if (App.DeviceUtils.IsHtml5) {
            if (!window["WebSocket"]) {
                Log.trace("不支持WebSocket");
                return;
            }
        }
        this._socket = new egret.WebSocket();
        if (this._msg instanceof ByteArrayMsg) {
            this._socket.type = egret.WebSocket.TYPE_BINARY;
        }
        Log.trace("WebSocket: " + this._host + ":" + this._port);
        this.addEvents();
        // this._socket.connect(this._host, this._port);
        this._socket.connectByUrl(this._url);
        App.EasyLoading.showLoading("连接服务器 ...");
    }

    /**
     * 重新连接
     */
    private reconnect():void {
        // 重连次数到达最大次数（重连超时）
        if ((this._reconnectCount > this._maxReconnectCount && this._reconnecting) || this._reconnectTimeout) {
            this._reconnectCount = 1;
            this._reconnecting = false;
            App.MessageCenter.dispatch(SocketConst.SOCKET_CLOSE);
        } else {
            if(this._startReconnect) {
                this._startReconnect = false;
            }
        }
        if (this._reconnecting || this._reconnectTimeout || this.isConnecting()) {
            return;
        }
        App.EasyLoading.showLoading("正在重连 ...");
        this._reconnecting = true;
        //var self = this;
        // this._reconnectTime毫秒执行一次重连， 执行this._maxReconnectCount次
        App.TimerManager.doTimer(this._reconnectTime, this._maxReconnectCount, function() {
            if(this.isConnecting()) {
                return;
            }
            this._reconnectCount++;
            if(App.IsNetAvaliable && !this._startReconnect) { // 网络可用
                this._startReconnect = true; // 开始重连
                App.MessageCenter.dispatch(SocketConst.SOCKET_START_RECONNECT);
                this.closeCurrentSocket();
                this.connect();
            }
        }, this, function(){
            if(!this.isConnecting()) {
                if(this._startReconnect == false) {
                    App.MessageCenter.dispatch(SocketConst.SOCKET_CLOSE);
                }
                this._reconnectTimeout = true;
            }
        }, this);
    }

    /**
     * 发送消息到服务器
     * @param msg
     */
    public send(msg:any, method:Function, methodObj:any):void {
        this._msg.send(this._socket, msg, method, methodObj);
    }

    /**
     * 关闭Socket连接
     */
    public close():void {
        this._connectFlag = false;
        this.closeCurrentSocket();
    }

    /**
     * 清理当前的Socket连接
     */
    private closeCurrentSocket(){
        this.removeEvents();
        this._socket.close();
        this._socket = null;
        this._msg.resetSid();
        this._isConnecting = false;
    }

    /**
     * Socket是否在连接中
     * @returns {boolean}
     */
    public isConnecting():boolean {
        return this._isConnecting;
    }

    /**
     * Debug信息
     * @param str
     */
    private debugInfo(str:String):void {
        App.MessageCenter.dispatch(SocketConst.SOCKET_DEBUG_INFO, str);
    }

}