class HomeView extends BaseEuiView {

	private scrollerGame: eui.Scroller;
	private lstGame: eui.List;
	private lbNick: eui.Label;
	private lbID: eui.Label;
	private lbGold: eui.Label;
	private btnExit: eui.Button;
	private imgHead: eui.Image;
	private gameData = [];
	private configData: any;

	public constructor($controller, $parent) {
		super($controller, $parent);
		this.skinName = "resource/skins/HomeSkin.exml";
	}

	public initUI(): void {
		super.initUI();
		this.lstGame = new eui.List();
		var layout: eui.HorizontalLayout = new eui.HorizontalLayout();
		layout.verticalAlign = egret.VerticalAlign.MIDDLE;
		layout.horizontalAlign = egret.HorizontalAlign.CENTER;
		layout.gap = 10;
		this.lstGame.layout = layout;
		this.lstGame.dataProvider = new eui.ArrayCollection();
		this.scrollerGame.viewport = this.lstGame;
		this.lstGame.addEventListener(eui.ItemTapEvent.ITEM_TAP, this.onChangeHandler, this);
		this.btnExit.addEventListener(egret.TouchEvent.TOUCH_TAP, this.onExitHandler, this);
		App.MessageCenter.addListener("event_change_gold", () => {
			this.lbGold.text = '金币：' + GameApp.chips2Money(GameApp.PlayerInfo.safeGold);
		}, this);
	}

	// 初始化数据
	public initData(): void {
		super.initData();
		this.getGameData();
		this.lstGame.dataProvider = new eui.ArrayCollection(this.gameData);
		this.lstGame.itemRendererSkinName = this.exml();
	}

	public open(...param: any[]): void {
		super.open(param);
		this.lbNick.text = '昵称：' + GameApp.PlayerInfo.nickName;
		this.lbID.text = 'ID：' + GameApp.PlayerInfo.id;
		this.lbGold.text = '金币：' + GameApp.chips2Money(GameApp.PlayerInfo.safeGold);
		this.imgHead.texture = GameApp.PlayerInfo.getHeadTexture();
	}

	private onChangeHandler(): void {
		App.SoundManager.playEffect("btnclick_mp3");
		let curGame = this.lstGame.selectedItem;
		let gameCfg = this.configData[curGame.id];
		if (gameCfg && gameCfg.play_state == 1) {
			App.EasyLoading.showLoading();
			GameApp.HomeManager.reqEnterCurGame(curGame.id);
		} else {
			GameApp.PromotManager.oneButtonTip('敬请期待');
		}
	}

	private getGameData(): void {
		this.configData = RES.getRes("game_config");
		if (this.configData) {
			for (var p in this.configData) {
				let data = this.configData[p];
				if(data.play_state == 1) {
					this.gameData.push(data);
				}
			}
			this.gameData.sort((a, b)=>{
				if(a.sort < b.sort) { 
					return -1;
				}
				if(a.sort > b.sort) {
					return 1;
				}
				return 0;
			});
		}
	}

	private exml() {
		return '<e:Skin xmlns:e="http://ns.egret.com/eui" xmlns:w="http://ns.egret.com/wing" states="up, down"><e:Image source="{data.icon}" alpha.up="1" alpha.down="0.8"/></e:Skin>';
	}

	private onExitHandler(ent: egret.TouchEvent): void {
		LoginReq.send_logout(function (success, msg) {
			if (success) {
				GameApp.PlayerInfo.logout();
				App.ViewManager.closeView(this);
				App.ViewManager.open(ViewConst.Login);
			} else {
				GameApp.PromotManager.floatingTip(msg.errorCode);
			}
		}, this)
	}

}