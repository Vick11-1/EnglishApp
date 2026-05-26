package com.englishbeginner.data

object DataPart7 {
    // Stage 12: 形容词 + Stage 13: 短句 + Stage 14-20
    val raw = """
12|big|大的|/bɪɡ/|谐音"比格"，比格大
12|small|小的|/smɔːl/|谐音"丝猫偶"，丝猫小
12|long|长的|/lɒŋ/|谐音"浪"，长浪
12|short|短的|/ʃɔːrt/|谐音"烧特"，烧短了
12|tall|高的|/tɔːl/|谐音"套偶"，套偶高
12|old|老的/旧的|/oʊld/|谐音"欧的"，欧老了
12|young|年轻的|/jʌŋ/|谐音"样"，年轻样子
12|new|新的|/njuː/|谐音"扭"，扭出新的
12|good|好的|/ɡʊd/|谐音"顾得"，顾得好
12|bad|坏的|/bæd/|谐音"败的"，败了坏了
12|hot|热的|/hɒt/|谐音"好特"，好特热
12|cold|冷的|/koʊld/|谐音"扣的"，扣的冷
12|warm|温暖的|/wɔːrm/|谐音"我木"，我木温暖
12|cool|凉爽的|/kuːl/|谐音"酷"，酷且凉爽
12|fast|快的|/fæst/|谐音"发死特"，快死了
12|slow|慢的|/sloʊ/|谐音"丝漏"，丝漏慢
12|hard|硬的/难的|/hɑːrd/|谐音"哈的"，硬的哈
12|soft|软的|/sɒft/|谐音"扫夫特"，软的扫
12|easy|容易的|/ˈiːzi/|谐音"衣子"，衣子容易
12|difficult|困难的|/ˈdɪfɪkəlt/|谐音"地飞考特"，地飞难
12|happy|快乐的|/ˈhæpi/|谐音"嗨皮"，嗨皮快乐
12|sad|悲伤的|/sæd/|谐音"塞的"，塞的悲伤
12|angry|生气的|/ˈæŋɡri/|谐音"安哥瑞"，安哥生气
12|afraid|害怕的|/əˈfreɪd/|谐音"啊飞的"，飞了害怕
12|beautiful|美丽的|/ˈbjuːtɪfəl/|谐音"碧优踢否"，美丽踢
12|ugly|丑的|/ˈʌɡli/|谐音"阿哥力"，阿哥丑
12|clean|干净的|/kliːn/|谐音"克里恩"，克里干净
12|dirty|脏的|/ˈdɜːrti/|谐音"德替"，脏的德
12|full|满的|/fʊl/|谐音"富偶"，满了富
12|empty|空的|/ˈɛmpti/|谐音"安扑替"，空的安
12|right|对的/右|/raɪt/|谐音"爱特"，对的爱
12|wrong|错的|/rɒŋ/|谐音"容"，错了容
12|same|相同的|/seɪm/|谐音"谁么"，相同的谁
12|different|不同的|/ˈdɪfrənt/|谐音"地佛认特"，不同
12|more|更多的|/mɔːr/|谐音"摸"，还要更多
12|less|更少的|/lɛs/|谐音"来丝"，来丝少
12|first|第一的|/fɜːrst/|谐音"佛斯特"，第一
12|last|最后的|/læst/|谐音"拉斯特"，最后
12|next|下一个的|/nɛkst/|谐音"耐客斯特"，下一个
12|early|早的|/ˈɜːrli/|谐音"饿离"，早饿了
12|late|迟的|/leɪt/|谐音"累特"，迟了累
12|near|近的|/nɪr/|谐音"你尔"，你近
12|far|远的|/fɑːr/|谐音"发"，发远了
12|high|高的|/haɪ/|谐音"嗨"，嗨得高
12|low|低的|/loʊ/|谐音"漏"，漏了低
12|light|轻的/亮的|/laɪt/|谐音"赖特"，轻的赖
12|heavy|重的|/ˈhɛvi/|谐音"海维"，海维重
12|thick|厚的|/θɪk/|谐音"西客"，厚的西
12|thin|薄的/瘦的|/θɪn/|谐音"新"，新且薄
12|wide|宽的|/waɪd/|谐音"歪的"，宽的歪
12|narrow|窄的|/ˈnæroʊ/|谐音"奶肉"，窄的奶
12|cheap|便宜的|/tʃiːp/|谐音"气扑"，气扑便宜
12|expensive|贵的|/ɪkˈspɛnsɪv/|谐音"一颗死喷细五"，贵死
12|safe|安全的|/seɪf/|谐音"谁夫"，谁夫安全
12|dangerous|危险的|/ˈdeɪndʒərəs/|谐音"灯脚热水"，危险
12|strong|强壮的|/strɒŋ/|谐音"死壮"，死壮
12|weak|弱的|/wiːk/|谐音"维克"，维克弱
12|quiet|安静的|/ˈkwaɪət/|谐音"快呃特"，快安静
12|loud|吵的|/laʊd/|谐音"闹的"，闹的吵
12|hungry|饿的|/ˈhʌŋɡri/|谐音"航哥瑞"，饿的
12|thirsty|渴的|/ˈθɜːrsti/|谐音"瑟斯替"，渴的
13|How are you?|你好吗？||打招呼最基本句型
13|I'm fine, thank you.|我很好，谢谢。||最经典回答
13|What's your name?|你叫什么名字？||问名字
13|My name is...|我叫...||自我介绍
13|Nice to meet you.|很高兴认识你。||初次见面
13|How old are you?|你多大了？||问年龄
13|I am ... years old.|我...岁了。||回答年龄
13|Where are you from?|你从哪来？||问来源
13|I'm from China.|我来自中国。||回答来源
13|What time is it?|几点了？||问时间
13|It's ... o'clock.|...点了。||回答时间
13|How much is this?|这个多少钱？||问价格
13|How many do you need?|你需要多少？||问数量
13|I want this one, please.|我想要这个。||购物用语
13|Can I help you?|需要帮忙吗？||服务用语
13|Yes, please.|好的，麻烦了。||礼貌接受
13|No, thank you.|不用了，谢谢。||礼貌拒绝
13|I'm sorry.|对不起。||道歉
13|That's OK.|没关系。||原谅别人
13|Excuse me.|打扰一下。||引起注意
13|You're welcome.|不客气。||回应感谢
13|See you later.|回头见。||告别
13|Goodbye.|再见。||告别
13|Have a nice day!|祝你愉快！||祝福语
13|I don't understand.|我不明白。||听不懂时
13|Please speak slowly.|请说慢一点。||请人慢说
13|I like it.|我喜欢。||表达喜好
13|I don't like it.|我不喜欢。||表达不喜欢
13|It's delicious.|真好吃。||评价食物
13|I'm full.|我饱了。||吃饱了
13|I'm hungry.|我饿了。||表达饿
13|I'm thirsty.|我渴了。||表达渴
13|Where is the bathroom?|洗手间在哪？||找洗手间
13|How do you say ... in English?|...英语怎么说？||问翻译
13|What does this mean?|这是什么意思？||问含义
13|I think so.|我也这么想。||同意
13|I don't think so.|我不这么想。||不同意
13|Let's go.|我们走吧。||提议出发
13|Wait a moment.|等一下。||让人等
13|No problem.|没问题。||答应请求
13|I agree.|我同意。||表达同意
13|I disagree.|我不同意。||表达不同意
13|It doesn't matter.|没关系。||安慰别人
13|Take care.|保重。||关心告别
13|Good morning.|早上好。||早上问候
13|Good afternoon.|下午好。||下午问候
13|Good evening.|晚上好。||晚上问候
13|Good night.|晚安。||睡前问候
13|How's the weather?|天气怎么样？||问天气
14|here|这里|/hɪr/|谐音"希尔"，这里
14|there|那里|/ðɛr/|谐音"泽尔"，那里
14|up|向上|/ʌp/|谐音"阿扑"，向上扑
14|down|向下|/daʊn/|谐音"倒恩"，向下倒
14|left|左边|/lɛft/|谐音"来夫特"，左边
14|right|右边|/raɪt/|谐音"爱特"，右边
14|front|前面|/frʌnt/|谐音"弗朗特"，前面
14|back|后面|/bæk/|谐音"拜客"，后面
14|inside|里面|/ˌɪnˈsaɪd/|in+side，里面边
14|outside|外面|/ˌaʊtˈsaɪd/|out+side，外面边
14|near|附近|/nɪr/|谐音"你尔"，你附近
14|far|远处|/fɑːr/|谐音"发"，发远处
14|between|中间|/bɪˈtwiːn/|谐音"比推恩"，中间
14|next to|旁边|/nɛkst tuː/|next+to，挨着
14|behind|后面|/bɪˈhaɪnd/|谐音"比嗨的"，比后面
14|beside|旁边|/bɪˈsaɪd/|谐音"比赛的"，比旁边
14|above|上方|/əˈbʌv/|谐音"阿八五"，上方
14|below|下方|/bɪˈloʊ/|谐音"比漏"，下方
14|under|下面|/ˈʌndər/|谐音"暗的"，下面暗
14|over|上方/越过|/ˈoʊvər/|谐音"欧五"，越过去
14|through|穿过|/θruː/|谐音"思入"，穿过去
14|across|横穿|/əˈkrɒs/|谐音"啊克劳斯"，横穿
14|around|周围|/əˈraʊnd/|谐音"啊让的"，周围
14|inside|里面|/ˌɪnˈsaɪd/|谐音"因赛的"，里面
14|outside|外面|/ˌaʊtˈsaɪd/|谐音"奥特赛的"，外面
15|car|汽车|/kɑːr/|谐音"卡"，汽车卡
15|bus|公共汽车|/bʌs/|谐音"巴死"，巴车
15|train|火车|/treɪn/|谐音"推恩"，火车推
15|plane|飞机|/pleɪn/|谐音"普雷恩"，飞普雷
15|bike|自行车|/baɪk/|谐音"拜客"，拜客骑车
15|boat|船|/boʊt/|谐音"抱特"，抱船
15|ship|轮船|/ʃɪp/|谐音"系铺"，系铺大船
15|taxi|出租车|/ˈtæksi/|谐音"泰克西"，泰克西
15|subway|地铁|/ˈsʌbweɪ/|谐音"撒布位"，撒布地下
15|truck|卡车|/trʌk/|谐音"踹客"，踹卡
15|motorcycle|摩托车|/ˈmoʊtərsaɪkl/|motor+cycle
15|helicopter|直升机|/ˈhɛlɪkɒptər/|谐音"海利靠扑特"
15|road|路|/roʊd/|谐音"肉的"，路
15|street|街道|/striːt/|谐音"死追特"，街道
15|highway|公路|/ˈhaɪweɪ/|谐音"嗨位"，嗨公路
15|airport|机场|/ˈɛrpɔːrt/|air+port，航空港
15|station|车站|/ˈsteɪʃən/|谐音"斯特诶深"，车站
15|ticket|票|/ˈtɪkɪt/|谐音"提客特"，拿票
15|passenger|乘客|/ˈpæsəndʒər/|谐音"拍森接"，乘客
15|driver|司机|/ˈdraɪvər/|谐音"拽五"，拽车司机
15|fuel|燃料|/fjuːəl/|谐音"飞优"，飞优燃料
15|gas|汽油|/ɡæs/|谐音"盖斯"，加油盖斯
15|parking|停车|/ˈpɑːrkɪŋ/|谐音"帕金"，停车帕金
15|traffic|交通|/ˈtræfɪk/|谐音"踹飞客"，交通
15|map|地图|/mæp/|谐音"买扑"，买地图
16|teacher|老师|/ˈtiːtʃər/|谐音"踢车"，踢车老师
16|doctor|医生|/ˈdɒktər/|谐音"道克特"，道克医生
16|nurse|护士|/nɜːrs/|谐音"纳斯"，纳护士
16|police|警察|/pəˈliːs/|谐音"坡力斯"，坡警
16|farmer|农民|/ˈfɑːrmər/|谐音"法默"，法农民
16|cook|厨师|/kʊk/|谐音"酷客"，酷客厨师
16|driver|司机|/ˈdraɪvər/|谐音"拽五"，拽车
16|worker|工人|/ˈwɜːrkər/|谐音"我可"，我可工作
16|artist|艺术家|/ˈɑːrtɪst/|谐音"阿替斯特"
16|singer|歌手|/ˈsɪŋər/|谐音"醒额"，醒歌手
16|dancer|舞者|/ˈdænsər/|谐音"蛋色"，跳舞蛋
16|writer|作家|/ˈraɪtər/|谐音"爱特"，写作
16|pilot|飞行员|/ˈpaɪlət/|谐音"派了特"，派飞
16|scientist|科学家|/ˈsaɪəntɪst/|谐音"赛恩替斯特"
16|engineer|工程师|/ˌɛndʒɪˈnɪr/|谐音"安金尼尔"
16|manager|经理|/ˈmænɪdʒər/|谐音"买你接"，经理买
16|student|学生|/ˈstjuːdənt/|谐音"死丢等特"，学生
16|secretary|秘书|/ˈsɛkrətɛri/|谐音"赛克瑞特瑞"
17|school|学校|/skuːl/|谐音"思顾"，学校
17|classroom|教室|/ˈklæsruːm/|class+room，班屋
17|teacher|老师|/ˈtiːtʃər/|谐音"踢车"，踢车老师
17|student|学生|/ˈstjuːdənt/|谐音"死丢等特"
17|book|书|/bʊk/|谐音"不可"，书不可少
17|pen|笔|/pɛn/|谐音"喷"，喷笔写
17|pencil|铅笔|/ˈpɛnsəl/|谐音"喷搜"，铅笔喷
17|ruler|尺子|/ˈruːlər/|谐音"入了"，入尺子
17|eraser|橡皮|/ɪˈreɪsər/|谐音"阿姨赛"，橡皮赛
17|paper|纸|/ˈpeɪpər/|谐音"胚泼"，纸胚泼
17|homework|作业|/ˈhoʊmwɜːrk/|home+work，家作
17|exam|考试|/ɪɡˈzæm/|谐音"一个赞姆"，考试
17|score|分数|/skɔːr/|谐音"死靠"，分数死靠
17|lesson|课|/ˈlɛsən/|谐音"来森"，来上课
17|subject|科目|/ˈsʌbdʒɪkt/|谐音"撒不杰克特"
17|math|数学|/mæθ/|谐音"卖死"，数学难
17|English|英语|/ˈɪŋɡlɪʃ/|谐音"因格利是"
17|history|历史|/ˈhɪstəri/|谐音"黑死特瑞"
17|science|科学|/ˈsaɪəns/|谐音"赛恩斯"，科学
17|music|音乐|/ˈmjuːzɪk/|谐音"缪斯克"，缪斯音乐
17|art|美术|/ɑːrt/|谐音"阿特"，阿特美术
17|PE|体育|/piː iː/|Physical Education缩写
17|library|图书馆|/ˈlaɪbrɛri/|谐音"莱博瑞"，图馆
17|playground|操场|/ˈpleɪɡraʊnd/|play+ground，玩地
17|lunchroom|食堂|/ˈlʌntʃruːm/|lunch+room
18|happy|开心的|/ˈhæpi/|谐音"嗨皮"
18|sad|难过的|/sæd/|谐音"塞的"
18|angry|生气的|/ˈæŋɡri/|谐音"安哥瑞"
18|scared|害怕的|/skɛrd/|谐音"死盖的"
18|excited|兴奋的|/ɪkˈsaɪtɪd/|谐音"一颗赛的"
18|tired|累的|/ˈtaɪərd/|谐音"太额的"
18|bored|无聊的|/bɔːrd/|谐音"伯的"
18|worried|担心的|/ˈwɜːrid/|谐音"我瑞的"
18|proud|自豪的|/praʊd/|谐音"扑绕的"
18|nervous|紧张的|/ˈnɜːrvəs/|谐音"呢五色"
18|surprised|惊讶的|/sərˈpraɪzd/|谐音"瑟扑瑞的"
18|confused|困惑的|/kənˈfjuːzd/|谐音"肯费优的"
18|relaxed|放松的|/rɪˈlækst/|谐音"瑞来克斯"
18|lonely|孤独的|/ˈloʊnli/|谐音"漏呢"
18|brave|勇敢的|/breɪv/|谐音"不瑞五"
18|kind|善良的|/kaɪnd/|谐音"开恩的"
18|jealous|嫉妒的|/ˈdʒɛləs/|谐音"接了色"
18|grateful|感激的|/ˈɡreɪtfəl/|谐音"格瑞特否"
19|shop|商店|/ʃɒp/|谐音"少铺"，商店
19|price|价格|/praɪs/|谐音"普爱斯"，价格
19|cheap|便宜的|/tʃiːp/|谐音"气扑"
19|expensive|贵的|/ɪkˈspɛnsɪv/|谐音"一颗死喷"
19|money|钱|/ˈmʌni/|谐音"骂你"，钱骂你
19|cash|现金|/kæʃ/|谐音"开事"，开现金
19|card|卡|/kɑːrd/|谐音"卡"，卡片
19|discount|折扣|/ˈdɪskaʊnt/|谐音"迪斯靠特"
19|sale|打折|/seɪl/|谐音"谁偶"，谁打折
19|receipt|收据|/rɪˈsiːt/|谐音"瑞吸特"，收据
19|change|零钱|/tʃeɪndʒ/|谐音"钱金"，零钱
19|pay|付钱|/peɪ/|谐音"赔"，付钱
19|buy|买|/baɪ/|谐音"拜"，拜托买
19|sell|卖|/sɛl/|谐音"塞偶"，卖
19|cost|花费|/kɒst/|谐音"靠斯特"，花费
19|spend|花费|/spɛnd/|谐音"死喷的"，花钱
19|save|存钱|/seɪv/|谐音"谁五"，存钱
19|cheap|便宜|/tʃiːp/|谐音"气扑"，便宜
19|quality|质量|/ˈkwɒləti/|谐音"扩力替"，质量
19|brand|品牌|/brænd/|谐音"不软的"，品牌
19|size|尺寸|/saɪz/|谐音"赛子"，尺寸
19|color|颜色|/ˈkʌlər/|谐音"卡了"，颜色
19|clothes|衣服|/kloʊðz/|谐音"克楼子"，衣服
19|try on|试穿|/traɪ ɒn/|试穿衣服
19|fit|合适|/fɪt/|谐音"非特"，非特合适
20|menu|菜单|/ˈmɛnjuː/|谐音"卖牛"，卖牛菜单
20|waiter|服务员|/ˈweɪtər/|谐音"喂特"，喂服务员
20|order|点餐|/ˈɔːrdər/|谐音"奥的"，奥的点餐
20|bill|账单|/bɪl/|谐音"比偶"，比账单
20|tip|小费|/tɪp/|谐音"提扑"，给小费
20|delicious|美味的|/dɪˈlɪʃəs/|谐音"地利是"，美味
20|taste|味道|/teɪst/|谐音"推斯特"，味道
20|hungry|饿的|/ˈhʌŋɡri/|谐音"航哥瑞"
20|thirsty|渴的|/ˈθɜːrsti/|谐音"瑟斯替"
20|water|水|/ˈwɔːtər/|谐音"挖特"
20|coffee|咖啡|/ˈkɒfi/|谐音"靠费"
20|tea|茶|/tiː/|谐音"踢"
20|rice|米饭|/raɪs/|谐音"爱斯"，米饭爱
20|noodle|面条|/ˈnuːdl/|谐音"怒豆"，怒吃面条
20|bread|面包|/brɛd/|谐音"不来的"，面包来了
20|meat|肉|/miːt/|谐音"蜜特"，蜜特肉
20|chicken|鸡肉|/ˈtʃɪkɪn/|谐音"起肯"，起肯鸡
20|vegetable|蔬菜|/ˈvɛdʒtəbl/|谐音"维杰特包"，蔬菜
20|fruit|水果|/fruːt/|谐音"弗入特"，水果
20|egg|鸡蛋|/ɛɡ/|谐音"爱格"，爱格蛋
20|milk|牛奶|/mɪlk/|谐音"密偶客"，牛奶
20|sugar|糖|/ˈʃʊɡər/|谐音"舒格"
20|salt|盐|/sɒlt/|谐音"扫特"
20|spicy|辣的|/ˈspaɪsi/|谐音"死败细"，辣的
20|sour|酸的|/ˈsaʊər/|谐音"骚"，酸的骚
20|sweet|甜的|/swiːt/|谐音"死威特"，甜的
"""
}
