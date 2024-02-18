module fish {
	export class MusicManager extends BaseClass {

		private allMusicLst: any[];
		private timeOnEnterFrame: number = 0;


		public constructor() {
			super();
			this.allMusicLst = [];
		}

		public destroy() {
			egret.stopTick(this.update, this);
			this.timeOnEnterFrame = 0;
			this.allMusicLst = [];
		}

		public init() {
			this.timeOnEnterFrame = egret.getTimer();
			egret.startTick(this.update, this);
		}

		public stopAllMusicEffect(): void {
			this.allMusicLst = [];
			//egret.stopTick(this.update, this);
		}

		public play(musicId: number): void {
			if (musicId && musicId != -1) {
				let musicSrc = FishMgr.Config.Music[musicId];
				let ret = this.analyze(musicSrc);
				if (ret.lst.length > 0) {
					this.allMusicLst.push(ret);
				}
			}
		}

		private analyze(data: any): any {
			let result = {
				id: data.id,
				lst: []
			}
			if (data) {
				let musicLst = data.mus_lst;
				let curIdx = 0;
				let item = { curAddTime: 0, curPlayIndex: 0, dataLst: [], playCmp: false };
				result.lst.push(item);
				let len = musicLst.length;

				for (let i = 0; i < len; i++) {
					result.lst[curIdx].dataLst.push(musicLst[i]);
					if (musicLst[i].nxt_id == -1 && i < len) {
						let item = { curAddTime: 0, curPlayIndex: 0, dataLst: [], playCmp: false };
						result.lst.push(item);
						curIdx = result.lst.length - 1;
					}
				}
			}
			return result;
		}

		public update(frameTime: number): boolean {
			let dt = frameTime - this.timeOnEnterFrame;
			this.timeOnEnterFrame = frameTime;
			for (let i = 0; i < this.allMusicLst.length; i++) {
				let item = this.allMusicLst[i];
				let itemCmp = true;
				for (let j = 0; j < item.lst.length; j++) {
					let subItem = item.lst[j];
					if (subItem.playCmp == false) {
						itemCmp = false;
						break;
					}
				}

				if (itemCmp) {
					this.allMusicLst.splice(i, 1);
					break;
				}
			}

			this.allMusicLst.forEach(item => {
				item.lst.forEach(subItem => {
					if (subItem.curPlayIndex < subItem.dataLst.length) {
						if (subItem.curAddTime >= subItem.dataLst[subItem.curPlayIndex].t) {
							let musicSrc = subItem.dataLst[subItem.curPlayIndex];
							let src = FishMgr.Config.Sound[musicSrc.src_id];
							let loop = musicSrc.loop;
							if (loop) {
								App.SoundManager.playEffect(src.file, 0);
							} else {
								App.SoundManager.playEffect(src.file);
							}
							subItem.curAddTime = 0;
							subItem.curPlayIndex++;
						} else {
							subItem.curAddTime += dt;
						}
					} else {
						subItem.playCmp = true;
					}
				});
			});
			return true;
		}
	}
}