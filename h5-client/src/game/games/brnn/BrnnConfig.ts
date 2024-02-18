module brnn {
	export enum CardType {
		FANG_KUAI = 0, //方块
		MEI_HUA = 1, //梅花
		HONG_TAO = 3, //红桃
		HEI_TAO = 4, //黑桃
		XIAO_WANG = 5, //小王
		DA_WANG = 6, //大王
	}

	export class BrnnConfig {
		// 图片资源(貌似没什么用)
		public static ImgResConfig = {
			"续押高亮": { key: "续押高亮", resPath: "game/bairenniuniu_std/resource/button/xy2.png" },
			"清空高亮": { key: "清空高亮", resPath: "game/bairenniuniu_std/resource/button/qk2.png" },
			"上分高亮": { key: "上分高亮", resPath: "game/bairenniuniu_std/resource/button/sf2.png" },
			"下分高亮": { key: "上分高亮", resPath: "game/bairenniuniu_std/resource/button/xf2.png" },
			"我要上庄": { key: "我要上庄", resPath: "game/bairenniuniu_std/resource/button/sz2.png" },
			"取消申请": { key: "取消申请", resPath: "game/bairenniuniu_std/resource/button/qxsq2.png" },
			"我要下庄": { key: "我要下庄", resPath: "game/bairenniuniu_std/resource/button/wyxz2.png" },
			"下注区域一": { key: "下注区域一", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注区域二": { key: "下注区域二", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注区域三": { key: "下注区域三", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注区域四": { key: "下注区域四", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码一": { key: "下注筹码一", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码二": { key: "下注筹码二", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码三": { key: "下注筹码三", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码四": { key: "下注筹码四", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码五": { key: "下注筹码五", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码六": { key: "下注筹码六", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码七": { key: "下注筹码七", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
			"下注筹码八": { key: "下注筹码八", resPath: "game/bairenniuniu_std/resource/image/xzk.png" },
		}
		// 特效资源
		public static EffectConfig = {
			"翻牌1": { key: "fanp", remarks: "翻牌1", file: "brnn_atlas2", picName: "fanp%d.png", frameTime: 0.1, beginFrame: 1, length: 8, isLoop: 0, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"翻牌2": { key: "fanp", remarks: "翻牌2", file: "brnn_atlas2", picName: "fanp%d.png", frameTime: 0.1, beginFrame: 9, length: 1, isLoop: 0, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"庄通吃": { key: "ztc", remarks: "庄通吃", file: "brnn_atlas2", picName: "tc%d.png", frameTime: 0.2, beginFrame: 1, length: 4, isLoop: 0, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"庄通赔": { key: "ztp", remarks: "庄通赔", file: "brnn_atlas2", picName: "tp%d.png", frameTime: 0.2, beginFrame: 1, length: 4, isLoop: 0, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"星星": { key: "broken_stars", remarks: "星星", file: "brnn_atlas2", picName: "Broken_stars_00%d.png", frameTime: 0.12, beginFrame: 1, length: 9, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"crying": { key: "crying", remarks: "crying", file: "brnn_atlas2", picName: "cry%d.png", frameTime: 0.1, beginFrame: 1, length: 3, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"10": { key: "niuniu", remarks: "牛牛", file: "brnn_atlas2", picName: "nn%d.png", frameTime: 0.2, beginFrame: 1, length: 5, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"11": { key: "sihua", remarks: "四花", file: "brnn_atlas2", picName: "shn%d.png", frameTime: 0.2, beginFrame: 1, length: 5, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"12": { key: "sizha", remarks: "四炸", file: "brnn_atlas2", picName: "szn%d.png", frameTime: 0.2, beginFrame: 1, length: 5, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"13": { key: "wuhua", remarks: "五花", file: "brnn_atlas2", picName: "whn%d.png", frameTime: 0.2, beginFrame: 1, length: 5, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, },
			"14": { key: "wuxiao", remarks: "五小", file: "brnn_atlas2", picName: "wxn%d.png", frameTime: 0.2, beginFrame: 1, length: 5, isLoop: 1, lifeTime: 0, isMove: 0, moveTime: 0, beginPos: "", endPos: "", musicId: 0, }
		}

		public static MusicConfig = {
			MusicTable: {
				"1": { id: 1, resPath: "brnn_music_BGSOUND_mp3", desc: "背景音乐", },
			},

			OtherTable: {
				"1": { id: 1, resPath: "brnn_music_gamestart_mp3", desc: "游戏开始发牌", },
				"2": { id: 2, resPath: "brnn_music_BANKER_LOSE_mp3", desc: "游戏结束后，所有闲家获胜时使用", },
				"3": { id: 3, resPath: "brnn_music_BANKER_WIN_mp3", desc: "游戏结束后，所有闲家失败时使用", },
				"4": { id: 4, resPath: "brnn_music_PAWN_BANKER_mp3", desc: "筹码移动时使用", },
				"5": { id: 5, resPath: "brnn_music_outcard_mp3", desc: "每次发牌时使用，发5张牌则使用5次", },
				"6": { id: 6, resPath: "brnn_music_OpenCard2_mp3", desc: "摊牌时使用", },
				"7": { id: 7, resPath: "brnn_music_TURN_CARD_mp3", desc: "倒计时剩余5秒后使用", },
				"8": { id: 8, resPath: "brnn_music_PlAYER_ADD_SCORE_mp3", desc: "闲家下注阶段开始时使用", },
			},

			MaleTable: {
				"2": { id: 2, resPath: "brnn_music_M_CHANGE_BANKER_mp3", desc: "男上庄3选1", },
				"3": { id: 3, resPath: "brnn_music_M_GET_BIG_BANKER_0_mp3", desc: "男上庄3选1", },
				"4": { id: 4, resPath: "brnn_music_M_GET_BIG_BANKER_1_mp3", desc: "男上庄3选1", },
				"6": { id: 6, resPath: "brnn_music_M_OX_4ZHA_mp3", desc: "闲四炸", },
				"7": { id: 7, resPath: "brnn_music_M_GIVE_UP_BANKER_1_mp3", desc: "闲不抢庄", },
				"8": { id: 8, resPath: "brnn_music_M_LOSE_0_mp3", desc: "男输，2选1", },
				"9": { id: 9, resPath: "brnn_music_M_LOSE_1_mp3", desc: "男输，2选1", },
				"10": { id: 10, resPath: "brnn_music_M_OX_0_mp3", desc: "闲无牛", },
				"11": { id: 11, resPath: "brnn_music_M_OX_1_mp3", desc: "闲牛1", },
				"12": { id: 12, resPath: "brnn_music_M_OX_2_mp3", desc: "闲牛2", },
				"13": { id: 13, resPath: "brnn_music_M_OX_3_mp3", desc: "闲牛3", },
				"14": { id: 14, resPath: "brnn_music_M_OX_4_mp3", desc: "闲牛4", },
				"15": { id: 15, resPath: "brnn_music_M_OX_5_mp3", desc: "闲牛5", },
				"16": { id: 16, resPath: "brnn_music_M_OX_6_mp3", desc: "闲牛6", },
				"17": { id: 17, resPath: "brnn_music_M_OX_7_mp3", desc: "闲牛7", },
				"18": { id: 18, resPath: "brnn_music_M_OX_8_mp3", desc: "闲牛8", },
				"19": { id: 19, resPath: "brnn_music_M_OX_9_mp3", desc: "闲牛9", },
				"20": { id: 20, resPath: "brnn_music_M_OX_NIUNIU_mp3", desc: "闲牛牛", },
				"21": { id: 21, resPath: "brnn_music_M_OX_XIAO_NIU_mp3", desc: "闲五小牛", },
				"23": { id: 23, resPath: "brnn_music_M_WIN_0_mp3", desc: "闲赢，2选1", },
				"24": { id: 24, resPath: "brnn_music_M_WIN_1_mp3", desc: "闲赢，2选1", },
				"25": { id: 25, resPath: "brnn_music_M_OX_TAURUS_mp3", desc: "闲五花", },
				"26": { id: 26, resPath: "brnn_music_M_OX_4HUA_mp3", desc: "闲四花", },
			},

			FemaleTable: {
				"2": { id: 2, resPath: "brnn_music_F_CHANGE_BANKER_mp3", desc: "女玩家上庄3选1", },
				"3": { id: 3, resPath: "brnn_music_F_GET_BIG_BANKER_0_mp3", desc: "女玩家上庄3选1", },
				"4": { id: 4, resPath: "brnn_music_F_GET_BIG_BANKER_1_mp3", desc: "女玩家上庄3选1", },
				"6": { id: 6, resPath: "brnn_music_F_OX_4ZHA_mp3", desc: "庄四炸", },
				"8": { id: 8, resPath: "brnn_music_F_LOSE_0_mp3", desc: "女输，2选1", },
				"9": { id: 9, resPath: "brnn_music_F_LOSE_1_mp3", desc: "女输，2选1", },
				"10": { id: 10, resPath: "brnn_music_F_OX_0_mp3", desc: "庄无牛", },
				"11": { id: 11, resPath: "brnn_music_F_OX_1_mp3", desc: "庄牛1", },
				"12": { id: 12, resPath: "brnn_music_F_OX_2_mp3", desc: "庄牛2", },
				"13": { id: 13, resPath: "brnn_music_F_OX_3_mp3", desc: "庄牛3", },
				"14": { id: 14, resPath: "brnn_music_F_OX_4_mp3", desc: "庄牛4", },
				"15": { id: 15, resPath: "brnn_music_F_OX_5_mp3", desc: "庄牛5", },
				"16": { id: 16, resPath: "brnn_music_F_OX_6_mp3", desc: "庄牛6", },
				"17": { id: 17, resPath: "brnn_music_F_OX_7_mp3", desc: "庄牛7", },
				"18": { id: 18, resPath: "brnn_music_F_OX_8_mp3", desc: "庄牛8", },
				"19": { id: 19, resPath: "brnn_music_F_OX_9_mp3", desc: "庄牛9", },
				"20": { id: 20, resPath: "brnn_music_F_OX_NIUNIU_mp3", desc: "庄牛牛", },
				"21": { id: 21, resPath: "brnn_music_F_OX_XIAO_NIU_mp3", desc: "庄五小牛", },
				"23": { id: 23, resPath: "brnn_music_F_WIN_0_mp3", desc: "女赢，2选1", },
				"24": { id: 24, resPath: "brnn_music_F_WIN_1_mp3", desc: "女赢，2选1", },
				"25": { id: 25, resPath: "brnn_music_F_OX_TAURUS_mp3", desc: "庄五花", },
				"26": { id: 26, resPath: "brnn_music_F_OX_4HUA_mp3", desc: "庄四花", },
			}
		}

		public static CardData = {
			//方块 A - K ; 0 - 12
			"0": { name: "方块 A", power: 1, mathpower: 1, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FKA", card_sm: "brnn_atlas3_json._FKA", card_mini: "cardxiao_01.png", },
			"1": { name: "方块 2", power: 2, mathpower: 2, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK2", card_sm: "brnn_atlas3_json._FK2", card_mini: "cardxiao_02.png", },
			"2": { name: "方块 3", power: 3, mathpower: 3, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK3", card_sm: "brnn_atlas3_json._FK3", card_mini: "cardxiao_03.png", },
			"3": { name: "方块 4", power: 4, mathpower: 4, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK4", card_sm: "brnn_atlas3_json._FK4", card_mini: "cardxiao_04.png", },
			"4": { name: "方块 5", power: 5, mathpower: 5, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK5", card_sm: "brnn_atlas3_json._FK5", card_mini: "cardxiao_05.png", },
			"5": { name: "方块 6", power: 6, mathpower: 6, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK6", card_sm: "brnn_atlas3_json._FK6", card_mini: "cardxiao_06.png", },
			"6": { name: "方块 7", power: 7, mathpower: 7, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK7", card_sm: "brnn_atlas3_json._FK7", card_mini: "cardxiao_07.png", },
			"7": { name: "方块 8", power: 8, mathpower: 8, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK8", card_sm: "brnn_atlas3_json._FK8", card_mini: "cardxiao_08.png", },
			"8": { name: "方块 9", power: 9, mathpower: 9, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK9", card_sm: "brnn_atlas3_json._FK9", card_mini: "cardxiao_09.png", },
			"9": { name: "方块 10", power: 10, mathpower: 10, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FK10", card_sm: "brnn_atlas3_json._FK10", card_mini: "cardxiao_10.png", },
			"10": { name: "方块 J", power: 11, mathpower: 10, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FKJ", card_sm: "brnn_atlas3_json._FKJ", card_mini: "cardxiao_11.png", },
			"11": { name: "方块 Q", power: 12, mathpower: 10, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FKQ", card_sm: "brnn_atlas3_json._FKQ", card_mini: "cardxiao_12.png", },
			"12": { name: "方块 K", power: 13, mathpower: 10, type: CardType.FANG_KUAI, card_big: "brnn_atlas3_json.FKK", card_sm: "brnn_atlas3_json._FKK", card_mini: "cardxiao_13.png", },


			//梅花 A - K ; 13 - 25
			"13": { name: "梅花 A", power: 1, mathpower: 1, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MHA", card_sm: "brnn_atlas3_json._MHA", card_mini: "cardxiao_14.png", },
			"14": { name: "梅花 2", power: 2, mathpower: 2, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH2", card_sm: "brnn_atlas3_json._MH2", card_mini: "cardxiao_15.png", },
			"15": { name: "梅花 3", power: 3, mathpower: 3, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH3", card_sm: "brnn_atlas3_json._MH3", card_mini: "cardxiao_16.png", },
			"16": { name: "梅花 4", power: 4, mathpower: 4, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH4", card_sm: "brnn_atlas3_json._MH4", card_mini: "cardxiao_17.png", },
			"17": { name: "梅花 5", power: 5, mathpower: 5, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH5", card_sm: "brnn_atlas3_json._MH5", card_mini: "cardxiao_18.png", },
			"18": { name: "梅花 6", power: 6, mathpower: 6, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH6", card_sm: "brnn_atlas3_json._MH6", card_mini: "cardxiao_19.png", },
			"19": { name: "梅花 7", power: 7, mathpower: 7, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH7", card_sm: "brnn_atlas3_json._MH7", card_mini: "cardxiao_20.png", },
			"20": { name: "梅花 8", power: 8, mathpower: 8, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH8", card_sm: "brnn_atlas3_json._MH8", card_mini: "cardxiao_21.png", },
			"21": { name: "梅花 9", power: 9, mathpower: 9, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH9", card_sm: "brnn_atlas3_json._MH9", card_mini: "cardxiao_22.png", },
			"22": { name: "梅花 10", power: 10, mathpower: 10, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MH10", card_sm: "brnn_atlas3_json._MH10", card_mini: "cardxiao_23.png", },
			"23": { name: "梅花 J", power: 11, mathpower: 10, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MHJ", card_sm: "brnn_atlas3_json._MHJ", card_mini: "cardxiao_24.png", },
			"24": { name: "梅花 Q", power: 12, mathpower: 10, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MHQ", card_sm: "brnn_atlas3_json._MHQ", card_mini: "cardxiao_25.png", },
			"25": { name: "梅花 K", power: 13, mathpower: 10, type: CardType.MEI_HUA, card_big: "brnn_atlas3_json.MHK", card_sm: "brnn_atlas3_json._MHK", card_mini: "cardxiao_26.png", },

			//红桃 A - K ; 26 - 38
			"26": { name: "红桃 A", power: 1, mathpower: 1, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RHA", card_sm: "brnn_atlas3_json._RHA", card_mini: "cardxiao_27.png", },
			"27": { name: "红桃 2", power: 2, mathpower: 2, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH2", card_sm: "brnn_atlas3_json._RH2", card_mini: "cardxiao_28.png", },
			"28": { name: "红桃 3", power: 3, mathpower: 3, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH3", card_sm: "brnn_atlas3_json._RH3", card_mini: "cardxiao_29.png", },
			"29": { name: "红桃 4", power: 4, mathpower: 4, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH4", card_sm: "brnn_atlas3_json._RH4", card_mini: "cardxiao_30.png", },
			"30": { name: "红桃 5", power: 5, mathpower: 5, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH5", card_sm: "brnn_atlas3_json._RH5", card_mini: "cardxiao_31.png", },
			"31": { name: "红桃 6", power: 6, mathpower: 6, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH6", card_sm: "brnn_atlas3_json._RH6", card_mini: "cardxiao_32.png", },
			"32": { name: "红桃 7", power: 7, mathpower: 7, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH7", card_sm: "brnn_atlas3_json._RH7", card_mini: "cardxiao_33.png", },
			"33": { name: "红桃 8", power: 8, mathpower: 8, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH8", card_sm: "brnn_atlas3_json._RH8", card_mini: "cardxiao_34.png", },
			"34": { name: "红桃 9", power: 9, mathpower: 9, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH9", card_sm: "brnn_atlas3_json._RH9", card_mini: "cardxiao_35.png", },
			"35": { name: "红桃 10", power: 10, mathpower: 10, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RH10", card_sm: "brnn_atlas3_json._RH10", card_mini: "cardxiao_36.png", },
			"36": { name: "红桃 J", power: 11, mathpower: 10, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RHJ", card_sm: "brnn_atlas3_json._RHJ", card_mini: "cardxiao_37.png", },
			"37": { name: "红桃 Q", power: 12, mathpower: 10, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RHQ", card_sm: "brnn_atlas3_json._RHQ", card_mini: "cardxiao_38.png", },
			"38": { name: "红桃 K", power: 13, mathpower: 10, type: CardType.HONG_TAO, card_big: "brnn_atlas3_json.RHK", card_sm: "brnn_atlas3_json._RHK", card_mini: "cardxiao_39.png", },

			//黑桃 A - K ; 39 - 51
			"39": { name: "黑桃 A", power: 1, mathpower: 1, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HTA", card_sm: "brnn_atlas3_json._HTA", card_mini: "cardxiao_40.png", },
			"40": { name: "黑桃 2", power: 2, mathpower: 2, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT2", card_sm: "brnn_atlas3_json._HT2", card_mini: "cardxiao_41.png", },
			"41": { name: "黑桃 3", power: 3, mathpower: 3, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT3", card_sm: "brnn_atlas3_json._HT3", card_mini: "cardxiao_42.png", },
			"42": { name: "黑桃 4", power: 4, mathpower: 4, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT4", card_sm: "brnn_atlas3_json._HT4", card_mini: "cardxiao_43.png", },
			"43": { name: "黑桃 5", power: 5, mathpower: 5, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT5", card_sm: "brnn_atlas3_json._HT5", card_mini: "cardxiao_44.png", },
			"44": { name: "黑桃 6", power: 6, mathpower: 6, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT6", card_sm: "brnn_atlas3_json._HT6", card_mini: "cardxiao_45.png", },
			"45": { name: "黑桃 7", power: 7, mathpower: 7, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT7", card_sm: "brnn_atlas3_json._HT7", card_mini: "cardxiao_46.png", },
			"46": { name: "黑桃 8", power: 8, mathpower: 8, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT8", card_sm: "brnn_atlas3_json._HT8", card_mini: "cardxiao_47.png", },
			"47": { name: "黑桃 9", power: 9, mathpower: 9, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT9", card_sm: "brnn_atlas3_json._HT9", card_mini: "cardxiao_48.png", },
			"48": { name: "黑桃 10", power: 10, mathpower: 10, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HT10", card_sm: "brnn_atlas3_json._HT10", card_mini: "cardxiao_49.png", },
			"49": { name: "黑桃 J", power: 11, mathpower: 10, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HTJ", card_sm: "brnn_atlas3_json._HTJ", card_mini: "cardxiao_50.png", },
			"50": { name: "黑桃 Q", power: 12, mathpower: 10, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HTQ", card_sm: "brnn_atlas3_json._HTQ", card_mini: "cardxiao_51.png", },
			"51": { name: "黑桃 K", power: 13, mathpower: 10, type: CardType.HEI_TAO, card_big: "brnn_atlas3_json.HTK", card_sm: "brnn_atlas3_json._HTK", card_mini: "cardxiao_52.png", },

			//大小王
			"52": { name: "小王", power: 14, mathpower: 0, type: CardType.XIAO_WANG, card_big: "brnn_atlas3_json.J2", card_sm: "brnn_atlas3_json._J2", card_mini: "cardxiao_53.png", },
			"53": { name: "大王", power: 15, mathpower: 0, type: CardType.DA_WANG, card_big: "brnn_atlas3_json.J1", card_sm: "brnn_atlas3_json._J1", card_mini: "cardxiao_54.png", },

			"54": { name: "背景", card_big: "brnn_atlas3_json.dp", card_sm: "brnn_atlas3_json.dp", card_mini: "cardxiao_55.png", },
			"55": { name: "横背景", card_big: "brnn_atlas3_json.dp1", card_sm: "brnn_atlas3_json.dp1", card_mini: "cardxiao_55.png", },
			"56": { name: "翻牌", card_big: "brnn_atlas3_json.f8", card_sm: "brnn_atlas3_json.f8", card_mini: "cardxiao_55.png", },
		}
	}

}