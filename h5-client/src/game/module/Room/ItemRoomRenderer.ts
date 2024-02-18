class ItemRoomRenderer extends eui.ItemRenderer {

	private lbTitle: eui.Label;
    private lbLimit: eui.Label;

	public constructor() {
		super();
	}

	public dataChanged() {
        super.dataChanged();
        if (this.lbTitle) {
            this.lbTitle.text = this.data.name;
            this.lbLimit.text = '入场：' + this.data.lower / 1000;
        }
    }

    public partAdded(partName:string, instance:any):void {
        super.partAdded(partName, instance);
        if (!this.data)
            return;
        if (instance == this.lbTitle) {
            this.lbTitle.text = this.data.name;
            this.lbLimit.text = '入场：' + this.data.lower / 1000;
        }
    }
}