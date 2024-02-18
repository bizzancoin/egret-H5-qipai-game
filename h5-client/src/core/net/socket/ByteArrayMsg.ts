/**
 * Created by yangsong on 15-2-11.
 */
class ByteArrayMsg implements BaseMsg {
    private _msgBuffer:egret.ByteArray;
    private _sid:number = 0;
    private actionArray:any[];

    /**
     * 构造函数
     */
    public constructor() {
        this._msgBuffer = new egret.ByteArray();
        this.actionArray = [];
    }

    /**
     * 接收消息处理
     * @param msg
     */
    public receive(socket:egret.WebSocket):void {
        socket.readBytes(this._msgBuffer);
        var obj:any = this.decode(this._msgBuffer);
        if (obj) {
            if (obj.sid > 0) {
                App.MessageCenter.dispatch('ssid_' + obj.sid, [obj.sid, obj.id, obj.body]);
            } else {
                obj.body.isInBackground = GameApp.isInBackground;
                App.MessageCenter.dispatch(obj.id, obj.body);
            }
        }
        if (this._msgBuffer.bytesAvailable == 0) {
            this._msgBuffer.clear();
        }
    }

    /**
     * 发送消息处理
     * @param msg
     */
    public send(socket:egret.WebSocket, msg:any, method:Function, methodObj:any):void {
        this._sid++;
        if(this._sid == 0) {
            this._sid++;
        }
        var obj:any = this.encode(msg);
        if (obj) {
            obj.position = 0;
            socket.writeBytes(obj, 0, obj.bytesAvailable);
            this.actionArray[this._sid] = [method, methodObj];
            App.MessageCenter.addListener('ssid_' + this._sid, this.handlerSessionMsg, this);
        }
    }

    private handlerSessionMsg(msg) {
        let ssid = msg[0];
        let msgid = msg[1];
        let body = msg[2];
        let listener = this.actionArray[ssid];
        if(listener) {
            listener[0].apply(listener[1], [msgid, body]);
        } else {
            Log.trace("sessionId 没有action");
        }
        App.MessageCenter.removeListener('ssid_' + ssid, this.handlerSessionMsg, this);
    }

    /**
     * 消息解析
     * @param msg
     */
    public decode(msg:any):any {
        Log.trace("decode需要子类重写，根据项目的协议结构解析");
        return null;
    }

    /**
     * 消息封装
     * @param msg
     */
    public encode(msg:any):any {
        Log.trace("encode需要子类重写，根据项目的协议结构解析");
        return null;
    }

    /**
     * sessionId
     */
    public resetSid(): void {
        this._sid = 0;
    }
    /**
     * 获取sessionDI
     */
    public getSid():number {
        return this._sid;
    }
}