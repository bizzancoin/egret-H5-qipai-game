class MsgFactory extends BaseClass {

	private reqMsg:any;
	private resMsg:any;

	private reqProto:any[];

	public constructor() {
		super();
	}

	public registerAll(): void {
		this.reqMsg = RES.getRes('reqMsg');
		for(let p in this.reqMsg) {
			let req = this.reqMsg[p];
			let proto = req.file;
			if (proto) {
				req.proto = dcodeIO.ProtoBuf.loadProto(proto);
			}
 		}
		this.resMsg = RES.getRes('resMsg');
		for(var p in this.resMsg) {
			let res = this.resMsg[p];
			let proto = res.file
			if (proto) {
				res.proto = dcodeIO.ProtoBuf.loadProto(proto);
			}
 		}
	}

	public encode(id, body) {
		let req = this.reqMsg[id];
		if(req) {
			let cls = req.proto.build(req.name);
			return new cls(body);
		}
		return null;
	}

	public decode(id, buffer) {
		let res = this.resMsg[id];
		if(!res) {
			res = this.reqMsg[id];
		}
		if(res) {
			let cls = res.proto.build(res.name);
			return cls.decode(buffer);
		} 
	}
}