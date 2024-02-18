/**
 * Created by yangsong on 15-1-14.
 * 音效类
 */
class SoundEffects extends BaseSound {
    private _volume: number;

    private _soundChannels: egret.SoundChannel[];

    /**
     * 构造函数
     */
    public constructor() {
        super();
        this._soundChannels = [];
    }

    /**
     * 播放一个音效
     * @param effectName
     */
    public play(effectName: string, loops?: number): void {
        var sound: egret.Sound = this.getSound(effectName);
        if (sound) {
            this.playSound(sound, loops);
        }
    }

    /**
     * 播放
     * @param sound
     */
    private playSound(sound: egret.Sound, loops?: number): void {
        let loopCount = 1;
        if (loops != null) {
            loopCount = loops;
        }
        var channel: egret.SoundChannel = sound.play(0, loopCount);
        channel.volume = this._volume;
        this._soundChannels.push(channel);
    }

    /**
     * 设置音量
     * @param volume
     */
    public setVolume(volume: number): void {
        this._volume = volume;
    }


    /**
     * 资源加载完成后处理播放
     * @param key
     */
    public loadedPlay(key: string): void {
        this.playSound(RES.getRes(key));
    }

    public stopEffect(): void {
        for (let i = 0; i < this._soundChannels.length; i++) {
            this._soundChannels[i].stop();
        }
        this._soundChannels = [];
    }
}