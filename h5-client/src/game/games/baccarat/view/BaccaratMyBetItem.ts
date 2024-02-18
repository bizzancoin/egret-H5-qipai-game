module baccarat {
	export class BaccaratMyBetItem {
		private grp: eui.Group;
		private lbBet: eui.BitmapLabel;
		public x: number;
		public y: number;
		public bet:number;

		public constructor(grp: eui.Group) {
			this.grp = grp;
			this.lbBet = <eui.BitmapLabel>grp.getChildAt(1);
			this.grp.visible = false;
			this.x = grp.x + grp.width / 2;
			this.y = grp.y + grp.height / 2;
		}

		public setMyBet(bet: number): void {
			this.grp.visible = true;
			this.lbBet.text = bet.toString();
			this.bet = bet;
		}
		public clear(): void {
			this.grp.visible = false;
			this.lbBet.text = "";
		}
		public myName():string{
			return this.grp.name;
		}
	}
}