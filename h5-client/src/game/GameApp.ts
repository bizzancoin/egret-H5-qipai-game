class GameApp {

    // 是否在后台运行
    public static isInBackground = false;
    /**
    * 通用提示
    */
    public static get PromotManager(): PromotManager {
        return PromotManager.getInstance();
    }

    /**
     * 用户信息
     */
    public static get PlayerInfo(): PlayerInfo {
        return PlayerInfo.getInstance();
    }

    /**
    * HomeManager
    */
    public static get HomeManager(): HomeManager {
        return HomeManager.getInstance();
    }

    public static get MarqueeManager(): MarqueeManager {
        return MarqueeManager.getInstance();
    }

    public static get HeartMgr(): HeartMgr {
        return HeartMgr.getInstance();
    }

    public static getMcFactory(json: string, png: string): egret.MovieClipDataFactory {
        let data = RES.getRes(json);
        let texture = RES.getRes(png);
        return new egret.MovieClipDataFactory(data, texture);
    }

    public static getClipByDataAndTexture(json: string, png: string, act: string): egret.MovieClip {
        let factory = GameApp.getMcFactory(json, png);
        if (factory) {
            return new egret.MovieClip(factory.generateMovieClipData(act));
        }
        return null;
    }

    public static getMovieDataByFileName(fileName: string, act: string): egret.MovieClipData {
        let factory = GameApp.getMcFactory(fileName + '_json', fileName + '_png');
        if (factory) {
            return factory.generateMovieClipData(act);
        }
        return null;
    }

    public static getClipByFileName(fileName: string, act: string): egret.MovieClip {
        return GameApp.getClipByDataAndTexture(fileName + '_json', fileName + '_png', act);
    }

    public static playMoveClip(parent: egret.DisplayObjectContainer, clip: egret.MovieClip, act: string, pos?: any,
        loop?: number, endCall?: Function, endCallObj?: any) {
        if (parent && clip && act) {
            parent.addChild(clip);
            if (pos) {
                clip.x = pos.x;
                clip.y = pos.y;
            }
            if (loop) {
                clip.gotoAndPlay(act, loop);
            } else {
                clip.gotoAndPlay(act);
            }
            clip.addEventListener(egret.Event.COMPLETE, (e: egret.Event) => {
                if (endCall) {
                    endCall.apply(endCallObj);
                }
            }, this);
        }
    }

    public static clone(obj: any): any {
        var o;
        if (typeof obj == "object") {
            if (obj === null) {
                o = null;
            } else {
                if (obj instanceof Array) {
                    o = [];
                    for (var i = 0, len = obj.length; i < len; i++) {
                        o.push(GameApp.clone(obj[i]));
                    }
                } else {
                    o = {};
                    for (var j in obj) {
                        o[j] = GameApp.clone(obj[j]);
                    }
                }
            }
        } else {
            o = obj;
        }
        return o;
    }

    public static chips2Money(chips): any
    {
        if (App.GlobalData.GoldMode) {
            return chips;
        }
        return (chips / 1000).toFixed(3);
    }

    public static money2Chips(money) {
        if (App.GlobalData.GoldMode) {
            return money;
        }
        return money * 1000;
    }

    public static chips2MoneyNum(chips): any
    {
        if (App.GlobalData.GoldMode) {
            return chips;
        }
        return chips / 1000;
    }

    // == 0 equal, > 0 large, < 0 less
    public equal(cLong1, cLong2): boolean {
        return cLong1.compare(cLong2) == 0;
    }

    public static Init(): void {
        PlayerManager.registerResListener();
        baccarat.BaccaratMgr.registerListener();
        fish.FishMgr.registerListener();
        brnn.BrnnMgr.registerListener();
    }
}