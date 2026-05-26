package com.englishbeginner.data

object DataPart2 {
    // Stage 1续: 序数词+量词 + Stage 2: 时间 + Stage 3: 月份季节
    val raw = """
1|first|第一|/fɜːrst/|谐音"佛斯特"，第一佛
1|second|第二|/ˈsɛkənd/|谐音"塞看的"，第二眼看
1|third|第三|/θɜːrd/|谐音"瑟的"，第三
1|fourth|第四|/fɔːrθ/|谐音"佛死"，第四
1|fifth|第五|/fɪfθ/|five去掉ve加fth
1|dozen|一打(12个)|/ˈdʌzən/|谐音"打针"，一打12个
1|pair|一对|/pɛr/|谐音"拍"，拍一对
1|half|一半|/hæf/|谐音"哈夫"，一半
1|quarter|四分之一|/ˈkwɔːrtər/|谐音"扩特"，一个季度
2|Monday|星期一|/ˈmʌndeɪ/|谐音"忙day"，周一忙
2|Tuesday|星期二|/ˈtjuːzdeɪ/|谐音"求死day"，周二求死
2|Wednesday|星期三|/ˈwɛnzdeɪ/|谐音"蚊子day"，周三打蚊子
2|Thursday|星期四|/ˈθɜːrzdeɪ/|谐音"瑟死day"，周四瑟瑟发抖
2|Friday|星期五|/ˈfraɪdeɪ/|谐音"弗来day"，周五不来
2|Saturday|星期六|/ˈsætərdeɪ/|谐音"赛特day"，周六赛特
2|Sunday|星期日|/ˈsʌndeɪ/|谐音"Sun+day"，太阳日
2|morning|早上|/ˈmɔːrnɪŋ/|谐音"摸宁"，早上摸鱼
2|afternoon|下午|/ˌæftərˈnuːn/|after+noon，中午之后
2|evening|晚上|/ˈiːvnɪŋ/|谐音"衣文宁"
2|night|夜晚|/naɪt/|谐音"耐特"，耐特晚上跑步
2|today|今天|/təˈdeɪ/|谐音"to+day"
2|tomorrow|明天|/təˈmɒroʊ/|谐音"特猫肉"，明天吃猫肉
2|yesterday|昨天|/ˈjɛstərdeɪ/|谐音"耶斯特day"
2|now|现在|/naʊ/|谐音"闹"，现在很闹
2|soon|不久|/suːn/|谐音"孙"，孙子不久就来
2|always|总是|/ˈɔːlweɪz/|谐音"哦哦喂子"，总是这样
2|never|从不|/ˈnɛvər/|谐音"耐我"，从不耐烦我
2|sometimes|有时|/ˈsʌmtaɪmz/|some+times，有些时候
2|usually|通常|/ˈjuːʒuəli/|谐音"悠肉"，通常悠着
2|early|早|/ˈɜːrli/|谐音"饿离"，早起饿了
2|late|晚|/leɪt/|谐音"累特"，晚了累了
2|hour|小时|/aʊər/|谐音"奥尔"，一个小时
2|minute|分钟|/ˈmɪnɪt/|谐音"密你特"，每一分钟
2|second|秒|/ˈsɛkənd/|谐音"塞看的"，每秒看一眼
2|clock|钟表|/klɒk/|谐音"克拉克"，克拉克钟
2|o'clock|...点钟|/əˈklɒk/|of the clock缩写
2|AM|上午|/eɪ ɛm/|Ante Meridiem，中午之前
2|PM|下午|/piː ɛm/|Post Meridiem，中午之后
3|January|一月|/ˈdʒænjueri/|谐音"粘牛爱瑞"，一月粘牛
3|February|二月|/ˈfɛbrueri/|谐音"飞不入爱瑞"，二月飞不入
3|March|三月|/mɑːrtʃ/|谐音"马去"，三月骑马去
3|April|四月|/ˈeɪprəl/|谐音"诶破"，四月诶破壳
3|May|五月|/meɪ/|谐音"妹"，五月小妹
3|June|六月|/dʒuːn/|谐音"住恩"，六月住恩
3|July|七月|/dʒuˈlaɪ/|谐音"猪来"，七月猪来
3|August|八月|/ˈɔːɡəst/|谐音"奥古斯特"，八月奥古
3|September|九月|/sɛpˈtɛmbər/|谐音"塞扑坦伯"，九月塞扑
3|October|十月|/ɒkˈtoʊbər/|谐音"奥克偷伯"，十月偷伯
3|November|十一月|/noʊˈvɛmbər/|谐音"挪完伯"，十一月挪完
3|December|十二月|/dɪˈsɛmbər/|谐音"地塞伯"，十二月地塞
3|spring|春天|/sprɪŋ/|谐音"死不拧"，春天拧不开
3|summer|夏天|/ˈsʌmər/|谐音"萨么"，夏天萨么热
3|autumn|秋天|/ˈɔːtəm/|谐音"奥疼"，秋天奥疼
3|winter|冬天|/ˈwɪntər/|谐音"吻特"，冬天吻特别冷
3|season|季节|/ˈsiːzən/|谐音"吸人"，季节吸人
3|week|周|/wiːk/|谐音"维克"，一周维克
3|month|月|/mʌnθ/|谐音"满死"，满月
3|year|年|/jɪr/|谐音"衣尔"，一年衣尔
3|birthday|生日|/ˈbɜːrθdeɪ/|birth+day，出生的日子
3|holiday|假日|/ˈhɒlɪdeɪ/|谐音"好力day"，假日好力玩
3|weekend|周末|/ˌwiːkˈɛnd/|week+end，周的结尾
3|festival|节日|/ˈfɛstɪvəl/|谐音"费死替我"，节日费钱
3|Christmas|圣诞节|/ˈkrɪsməs/|谐音"克瑞斯木斯"，圣诞老人
3|New Year|新年|/njuː jɪr/|new+year，新的一年
"""
}
