class PromotManager extends BaseClass {
    private floatTip: FloatingTip;
    private btnTip: ButtonTip;

    public constructor() {
        super();
        this.floatTip = FloatingTip.getInstance();
        this.btnTip = ButtonTip.getInstance();
    }

    public floatingTip(tip: string): void {
        if (!tip) {
            return;
        }
        if(tip.toString() == "400"){
            return;
        }
        this.floatTip.showTips(this.getMsg(tip));
    }

    public oneButtonTip(tip: string, args?: any, method?: Function, methodObj?: any) {
        if (!tip) {
            return;
        }
        if(tip.toString() == "400"){
            return;
        }
        let msg = this.getMsg(tip);
        msg = this.contactArgs(msg, args);
        this.btnTip.oneButtonTip(msg, method, methodObj);
    }

    public twoButtonTip(tip: string, args?: any, sureMethod?: Function, sureMethodObj?: any, cancelMethod?: Function, cancelMethodObj?: any) {
        if (!tip) {
            return;
        }
        let msg = this.getMsg(tip);
        msg = this.contactArgs(msg, args);
        this.btnTip.twoButtonTip(msg, sureMethod, sureMethodObj, cancelMethod, cancelMethodObj);
    }

    public hideButtonTip() {
        this.btnTip.hideButtonTip();
    }

    private getMsg(key: any): string {
        let message = RES.getRes('message_config');
        if (message[key]) {
            return message[key].name;
        }
        return key;
    }

    private contactArgs(msg: string, args: any) {
        if (msg && msg.length > 0) {
            if (args && args.length > 0) {
                for (let i = 0; i < args.length; i++) {
                    msg = msg.replace('{' + (i + 1) + '}', args[i]);
                }
            }
        }
        return msg;
    }
}