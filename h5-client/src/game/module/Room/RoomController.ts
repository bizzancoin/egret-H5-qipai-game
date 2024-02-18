class RoomController extends BaseController {

	private roomView: RoomView;

	public constructor() {
		super();
		this.roomView = new RoomView(this, LayerManager.UI_Main);
		App.ViewManager.register(ViewConst.Room, this.roomView);
	}
}