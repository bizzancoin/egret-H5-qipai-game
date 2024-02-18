module fish {
	export class FishLoadingView extends BaseLoadingView {

		public loadingBgRes(): string {
			return FishMgr.fishModule + '_fish_loading_json.loadingbg';
		}

		public fillBgRes(): string {
			return FishMgr.fishModule + '_fish_loading_json.loadbg';
		}

		public fillRes(): string {
			return FishMgr.fishModule + '_fish_loading_json.loadt';
		}
	}
}