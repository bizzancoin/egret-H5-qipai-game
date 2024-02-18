//////////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (c) 2014-present, Egret Technology.
//  All rights reserved.
//  Redistribution and use in source and binary forms, with or without
//  modification, are permitted provided that the following conditions are met:
//
//     * Redistributions of source code must retain the above copyright
//       notice, this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above copyright
//       notice, this list of conditions and the following disclaimer in the
//       documentation and/or other materials provided with the distribution.
//     * Neither the name of the Egret nor the
//       names of its contributors may be used to endorse or promote products
//       derived from this software without specific prior written permission.
//
//  THIS SOFTWARE IS PROVIDED BY EGRET AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
//  OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
//  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//  IN NO EVENT SHALL EGRET AND CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
//  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;LOSS OF USE, DATA,
//  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
//  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
//  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
//  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
//////////////////////////////////////////////////////////////////////////////////////

class Main extends eui.UILayer {

    protected createChildren(): void {
        super.createChildren();

        egret.lifecycle.addLifecycleListener((context) => {
            
        });

        document.addEventListener("visibilitychange", ()=>{
            if (document.visibilityState == "visible") {
                if(!egret.Capabilities.isMobile) {
                    GameApp.isInBackground = false;
                    GameApp.HomeManager.resumeGame();
                }
            }
            
            if (document.visibilityState == "hidden") {
                if(!egret.Capabilities.isMobile) {
                    GameApp.isInBackground = true;
                    GameApp.HomeManager.pauseGame();
                }
            }
        });

        // app 进入后台(非手机浏览器，一直运行)
        egret.lifecycle.onPause = () => {
            if(egret.Capabilities.isMobile) {
                GameApp.isInBackground = true;
                egret.ticker.pause();
                App.SoundManager.setBgVolume(0);
                App.SoundManager.setEffectVolume(0);
                GameApp.HomeManager.pauseGame();
            }
        }

        // app 进入前台(非手机浏览器，一直运行)
        egret.lifecycle.onResume = () => {
            if(egret.Capabilities.isMobile) {
                GameApp.isInBackground = false;
                egret.ticker.resume();
                App.SoundManager.setBgVolume(1);
                App.SoundManager.setEffectVolume(1);
                GameApp.HomeManager.resumeGame();
            }
        }
        //inject the custom material parser
        //注入自定义的素材解析器

        let assetAdapter = new AssetAdapter();
        egret.registerImplementation("eui.IAssetAdapter", assetAdapter);
        egret.registerImplementation("eui.IThemeAdapter", new ThemeAdapter());
        App.EasyLoading.showLoading();
        this.loadConfig();
    }


    // 加载配置文件
    private loadConfig(): void {
        App.ResourceUtils.addConfig("resource/default.res.json", "resource/");
        App.ResourceUtils.loadConfig(this.onConfigComplete, this);
    }

    // 配置文件加载完毕
    private onConfigComplete(): void {
        var theme = new eui.Theme("resource/customer_theme.json", this.stage);
        theme.addEventListener(eui.UIEvent.COMPLETE, this.onThemeLoadComplete, this);
    }

    private onThemeLoadComplete(): void {
        // 预加载资源
        App.ResourceUtils.loadGroup('preload', this.onPreLoadComplete, this.onPreLoadProgress, this);
    }

    private onPreLoadComplete(): void {
        App.EasyLoading.hideLoading();
        LoginMgr.start();
    }

    private onPreLoadProgress(itemsLoaded: number, itemsTotal: number) {

    }

}
