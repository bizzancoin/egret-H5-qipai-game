class BaseLoadingView extends BaseEuiView {
	private imgBg: eui.Image;
	private txtMsg: eui.Label;
	private progressBar: eui.ProgressBar;

	public constructor($controller, $parent) {
		super($controller, $parent);
		this.skinName = 'resource/skins/LoadingSkin.exml'
	}

	public initUI(): void {
		super.initUI();
		this.txtMsg.visible = false;

		this.imgBg.texture = RES.getRes(this.loadingBgRes());

		this.progressBar.skinName = 'resource/eui_skins/ProgressBarSkin.exml';

		// 进度条填充
		let imgFill = <eui.Image>this.progressBar.thumb;
		imgFill.texture = RES.getRes(this.fillRes());

		// 进度条背景
		let imgFillBg = <eui.Image>this.progressBar.getChildByName('imgBg');
		imgFillBg.texture =RES.getRes(this.fillBgRes());

		this.progressBar.width = imgFillBg.width;
		this.progressBar.height = imgFillBg.height;
		this.progressBar.minimum = 0;
		this.progressBar.maximum = 100;
		this.progressBar.value = 0;

		this.progressBar.labelDisplay.textColor = 0xffffff;
		this.progressBar.labelDisplay.size = 60;
		this.progressBar.labelDisplay.horizontalCenter = 0;
		this.progressBar.labelDisplay.bottom = 20;

		this.progressBar.visible = this.showProgressBar();
	}

	public loadingBgRes(): string {
		return '';
	}

	public fillBgRes(): string {
		return '';
	}

	public fillRes(): string {
		return '';
	}

	public showProgressBar(): boolean {
		return true;
	}

	public setProgress(current: number, total: number) {
		//this.txtMsg.text = "资源加载中..." + current + "/" + total;
		//this.progressBar.maximum = total;
		this.progressBar.value = current / total * 100;
	}
}