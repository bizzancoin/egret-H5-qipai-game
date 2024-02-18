/**
 * Created by yangsong on 15-3-25.
 */
class ByteArrayMsgByProtobuf extends ByteArrayMsg {

    /**
     * 构造函数
     */
    public constructor() {
        super();
    }

   
    /**
     * 消息解析
     * @param msg
     */
    public decode(msg:any):any {
        //var msgID = msg.readShort();
        var len = msg.readShort();
        if (msg.bytesAvailable >= len + 8) {
            var sid = msg.readInt();
            var msgID = msg.readInt();
            var bytes:egret.ByteArray = new egret.ByteArray();
            msg.readBytes(bytes, 0, len);

            var obj:any = {};
            obj.id = msgID;
            obj.sid = sid;
            obj.body = App.MsgFactory.decode(msgID, bytes.buffer);
            return obj;
        }
        return null;
    }

    /**
     * 消息封装
     * @param msg
     */
    public encode(msg:any):any {
        var msgID = msg.id;
        var msgBody = App.MsgFactory.encode(msgID, msg);

        var bodyBytes:egret.ByteArray = new egret.ByteArray(msgBody.toArrayBuffer());

        var msglen = bodyBytes.length;
        var key = this.getSid() ^ msglen ^ 0x200;

        var sendMsg:egret.ByteArray = new egret.ByteArray();
        sendMsg.writeShort(msglen); 
        sendMsg.writeInt(key);
        sendMsg.writeInt(msgID);
        sendMsg.writeBytes(bodyBytes);
        return sendMsg;
    }
}