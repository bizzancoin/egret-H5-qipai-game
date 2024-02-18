class RoomView extends BasePanelView {

	private lstRoom: eui.List;

	public constructor(controller: BaseController, parent: eui.Group) {
		super(controller, parent);
		this.title = "选择场次";
	}

	public initData(): void {
		super.initData();
		this.lstRoom = new eui.List();
		this.lstRoom.addEventListener(eui.ItemTapEvent.ITEM_TAP, this.onChangeHandler, this);
	}


	public open(...param: any[]): void {
		super.open(param);
		if (param && param[0]) {
			this.title = param[0];
			this.lbTitle.text = this.title;
		}
		let rooms: eui.ArrayCollection = new eui.ArrayCollection();
		for (let i: number = 0; i < GameApp.PlayerInfo.myRoomTypeInfo.length; i++) {
			let tmps = GameApp.PlayerInfo.myRoomTypeInfo[i].rooms;
			for (let j = 0; j < tmps.length; j++) {
				rooms.addItem(tmps[j]);
			}
		}
		this.createList(rooms);
	}

	private createList(array: eui.ArrayCollection): void {
		var layout: eui.HorizontalLayout = new eui.HorizontalLayout();
		layout.verticalAlign = egret.VerticalAlign.MIDDLE;
		layout.horizontalAlign = egret.HorizontalAlign.CENTER;
		layout.gap = 0;

		this.lstRoom.layout = layout;
		this.lstRoom.itemRenderer = ItemRoomRenderer;
		this.lstRoom.itemRendererSkinName = "resource/skins/ItemRoomSkin.exml";
		this.lstRoom.dataProvider = array;

		var scroller: eui.Scroller = new eui.Scroller();
		scroller.percentWidth = scroller.percentHeight = 100;
		scroller.viewport = this.lstRoom;

		this.contentGroup.addChild(scroller);
		this.lstRoom.addEventListener(eui.ItemTapEvent.ITEM_TAP, this.onChangeHandler, this);
	}


	private onChangeHandler(): void {
		let curRoom = this.lstRoom.selectedItem;
		if (GameApp.PlayerInfo.safeGold < curRoom.lower.toNumber()) {
			GameApp.PromotManager.floatingTip("金币不足");
			return;
		} else if (GameApp.PlayerInfo.safeGold > curRoom.upper.toNumber()) {
			
			GameApp.PromotManager.floatingTip("金币超出房间设置");
			return;
		}
		App.EasyLoading.showLoading();
		GameApp.HomeManager.setRoomInfo(curRoom);
		GameApp.HomeManager.reqQuickEnterCurGame(curRoom.roomId);
	}

}