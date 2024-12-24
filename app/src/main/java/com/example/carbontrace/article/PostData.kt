package com.example.carbontrace.article

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.carbontrace.R

//页面附加信息：作者、上传时间、最小阅读时间
data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int,
)

//作者信息
data class PostAuthor(
    val name: String,
    @DrawableRes val imageId: Int
)
val sakiko = PostAuthor("丰川祥子",R.drawable.sakiko)
val hospital = PostAuthor("南京理工大学校医院",R.drawable.hospital)
val health = PostAuthor("健康生活",R.drawable.health)

//文章段落，文章整体是由段落组成的列表（后续可以实现插入图片功能）
data class Paragraph(
    val text: String
)
val paragraphsPost1 = listOf(
    Paragraph(
        "世界献血日，也称为世界献血者日，定于每年的 6 月 14 日。"),
    Paragraph(
        "为了鼓励更多的人无偿献血，宣传和促进全球血液安全规划的实施，世界卫生组织、红十字会与红新月会国际联合会、国际献血组织联合会、国际输血协会将 2004 年 6 月 14 日定为第一个世界献血者日。" +
                "之所以选定这一天，是因为 6 月 14 日是发现 ABO 血型系统的诺贝尔奖获得者卡尔·兰德斯坦纳的生日。"),
    Paragraph(
        "试着回答下面几个问题，注意你只有一次机会，中途退出时选项不会保存。全部正确可以得到积分。")
)
val paragraphsPost2 = listOf(
    Paragraph(
        "为了方便师生在校内享受南京市高水平医疗资源，校医院开展”名医进校园”系列活动，十一月份邀请南京脑科专家来校义诊"),
    Paragraph(
        "具体安排如下："),
    Paragraph(
        "时间：【11月14日（本周四），9：00-12：00】"),
    Paragraph(
        "地点：校医院【二楼内科诊室】"),
    Paragraph(
        "流程：无需挂号，直接到诊室就诊"),
    Paragraph(
        "专家：李毅 南京脑科医院副主任医师"),
    Paragraph(
        "李毅，从事精神卫生邻域的临床实践工作达30年，积累了丰富的工作经验。" +
                "对于精神分裂症、抑郁症、各种器质性精神障碍等精神科的常见疾病、多发病及各种疑难杂症均有丰富的临床诊疗经验。擅长器质性精神障碍、抑郁症、神经症等的诊治"),
)
val paragraphPost3 = listOf(
    Paragraph("5月11日，是世界卫生组织认定的“世界防治肥胖日”。目前，肥胖被世界卫生组织确定为十大慢性疾病之一，也是世界范围内最严重的公共卫生问题之一，是仅次于吸烟的致死原因。\n" +
            "《中国居民膳食指南（2022）》显示，超重肥胖及慢性病问题日趋严重：目前我国成年居民超重或肥胖已经超过一半（50.7%），6岁以下和6至17岁儿童青少年超重肥胖率分别达到10.4%和19.0%。18岁及以上居民超重率和肥胖率，分别为34.3%和16.4%。\n"),
    Paragraph("国际上判断是否肥胖最简单的标准，是身体质量指数（BMI），BMI的测算方法为体重（kg）除以身高（m）的平方。正常健康的成年人BMI应该在18.5-23.9之间，如果BMI大于24，但小于28，属于偏胖体型；如果BMI突破28，即是世界卫生组织确认的疾病——肥胖症。\n" +
            "中山大学附属第三医院副院长、内分泌与代谢病学科主任医师陈燕铭向21世纪经济报道记者表示，目前肥胖症有着年轻化趋势，青少年儿童和20-30岁青年群体患肥胖症的情况逐渐增多。此外，现代人久坐、熬夜、长时间使用电子产品等不健康的生活方式都可能会引起肥胖等问题。\n"),
    Paragraph("何为真实的肥胖？"),
    Paragraph("日常生活中，经常碰到瘦子天天喊减肥，真正的胖子却不觉得自己胖，而从健康的角度如何科学定义肥胖？不提倡一味地以瘦为美，但也要对肥胖症有科学认知，过度肥胖不仅是美观问题，更是病得治。"),
    Paragraph("减肥的意义：抵御各种疾病风险"),
    Paragraph("减肥的意义对于健康来说非同凡小。从心血管疾病，到癌症无一不于此有或多或少的联系。\n" +
            "2021年4月，美国心脏协会(AHA)发布了肥胖与心血管疾病的科学声明。肥胖对动脉粥样硬化性心血管疾病、心力衰竭和心律失常特别是心源性猝死以及房颤诊断、临床管理以及预后均有影响。肥胖是一种复杂的慢性疾病可损害身体健康，增加长期并发症风险缩短寿命。\n" +
            "最为致命的是肥胖与癌症的关系一向广受关注。\n"),
    Paragraph("目前，已经证实肥胖与至少12种癌症类型相关。身体质量指数（BMI）常被作为肥胖的评估指标, 此外，腰围（WC）也作为中心性肥胖的评价标准之一，已被认为会增加几种癌症类型的风险，并能更好地分辨与肥胖相关的结肠癌和绝经后乳腺癌的风险。"),
    Paragraph("此外，也有研究显示，患有肥胖症的患者感染新冠肺炎的风险越高，在纽约市一家三甲医院中，对1150名新冠肺炎患者进行分析，发现在病情危重的257患者中，212名（82%）病人至少有一种慢性病，119名（46%）患者有肥胖症（定义为BMI≥30），92名（36%）的患者有糖尿病，表明肥胖症和糖尿病与新冠肺炎的严重程度和死亡率有关。"),
    Paragraph("陈燕铭向21世纪经济报道记者表示，基体肥胖会出现免疫功能低下的情况，一旦感染新冠病毒容易出现重症以及感染扩散。"),
    Paragraph("科学减肥并不意味着一味地节食，营养管理可以通过总热量递减以及主食递减的方法进行合理控制，科学的营养管理还需配合恰当的运动干预，才能达到期望的减重效果。"),
    Paragraph("在药物治疗方面，奥司利他是国家药监局批准的唯一合法肥胖症治疗药。其主要作用原理为服用后穿过胃肠道并吸收的过程中，可抑制与之接触的脂肪酶的活性。但是，奥司利他并不能阻止脂肪合成，因此，若服用药物期间仍摄入米饭、馒头等大量碳水化合物，易转化成脂肪储存，仍然有肥胖的风险。只有在“管住嘴”的前提下，控制脂肪和碳水化合物摄入，才会有较明显效果。"),
    Paragraph("此外，奥司利他可能会伴有头痛、腹泻、月经紊乱、过敏等不良反应，若长期使用，还需定期检查肝肾功能。"),
    Paragraph("目前，被广泛接受的代谢减重手术主要有胃袖状切除术、旁路术、胆胰转流十二指肠转位术。我国较为领先的诊疗模式是“3+N”减重多学科联合诊疗模式，是以内分泌科为主导，在传统的营养干预、运动干预、心理干预基础上，联合中医药针灸，必要时代谢手术治疗的一体化诊疗模式。手术治疗后还需医疗团队进行追踪随访，保持患者体内环境稳态，防止体重反弹、大幅波动等情况出现。"),
    Paragraph("胡细玲称，减重是个持久战，贵在患者的坚持和改善不良的行为和生活模式。"),
    Paragraph("试着回答下面几个问题，注意你只有一次机会，中途退出时选项不会保存。全部正确可以得到积分。")
)

val paragraphPost4 = listOf(
    Paragraph("冬季气温降低，对于老年人来说是各类疾病的高发季节，由于其自身免疫力下降，对寒冷的抵抗力较弱，容易感染疾病。同时气温低很可能会导致老年人血管收缩，血压升高、心脏负担加重等问题。种种原因都意味着在冬天老年人需要更加注重防护。"),
    Paragraph("下面分享一些冬季防护小常识。"),
    Paragraph("1、起床要缓"),
    Paragraph("冬季是冠心病、急性心肌梗死的高发期。寒冷刺激会引起血管收缩，导致血压升高。因此，中老年人在每日起床时，要以“缓”为要，牢记这3个“一分钟”：醒来后在床上躺一分钟；然后慢慢起来在床边坐一分钟；再在床边站一分钟，之后再走动。"),
    Paragraph("2、及时补充水分"),
    Paragraph("冬季天气寒冷、干燥，再加上室内温度不低、蒸发量大，若不能及时补充水分，很容易造成血液黏稠度升高，进而诱发脑卒中等意外。建议晨起和夜间都喝杯温水，小口小口喝下。睡前喝200ml水，有助于降低血黏度；起床后喝200ml水，能稀释血液。同时可以在室内放置加湿器或洒水，保持室内湿度适中，避免呼吸道干燥不适。"),
    Paragraph("3、注意饮食“四忌”"),
    Paragraph("老年人应保持清淡、营养均衡的饮食，多吃富含蛋白质、维生素和纤维素的食物，同时要控制盐、糖和油的摄入量，避免过度饮食，严格遵守饮食“四忌”。"),
    Paragraph("忌多：应少吃多餐，忌暴饮暴食。饮食过多，会使胃肠发生急性扩张，引起胃肠消化吸收功能紊乱。"),
    Paragraph("忌硬：人到老年，牙齿松动，消化功能低下，食物宜切碎煮烂，蔬菜宜用嫩叶，忌进食过硬和油炸或过干食物。"),
    Paragraph("忌偏食：老年人饮食应多样化，合理搭配食物，注意补充些含铁、钙丰富的食物；因铁是合成血红蛋白的主要成分；缺钙易发生骨骼胶钙和骨质疏松。"),
    Paragraph("忌寒：老人体多阴虚，不喜寒，饮食宜热，可暖胃养身，尤其在寒冷的冬天。"),
    Paragraph("4、适当体育锻炼"),
    Paragraph("在冬季老年人应在力所能及的情况下坚持每天锻炼，这对增强体质、防病保健大有裨益。在晴朗少风的天气适当户外活动可预防疾病，提高御寒能力，但是要注意运动的量和方式。"),
    Paragraph("在锻炼时，老年人应选择适合自己的运动方式，如散步、太极拳、瑜伽等，避免过度运动和受伤。同时，要遵循科学的运动原则，适量逐步增加运动强度和时间，以避免对身体造成不良影响。"),
    Paragraph("从医学角度来看，冬季锻炼可以帮助老年人保持身体健康。适量的运动可以促进血液循环，增加身体抵抗力，减少心脑血管疾病的发生。此外，锻炼还可以增强老年人的呼吸系统功能，改善肺活量和呼吸质量。冬季气候寒冷，容易导致呼吸道疾病的发生。通过锻炼，老年人可以增强呼吸道的免疫力，减少呼吸道疾病的发生。"),
    Paragraph("希望这些小常识能够帮助老年人在冬季保持身体健康，度过一个健康、温暖的冬季，享受幸福美满的晚年生活。")
)

val paragraphPost5 = listOf(
    Paragraph("节日期间，亲人团聚，朋友来访，丰盛的家宴为节日增添快乐与喜庆的气氛。然而，随着生活方式的改变，佳节饮食也应追求吃得科学，吃得卫生，吃得健康。那么，佳节饮食怎样安排，才科学合理，益于健康呢？"),
    Paragraph("圣诞节饮食营养巧搭配"),
    Paragraph("品种巧搭配"+
        "选购菜肴和节日饮食的安排。应在注重色、香、味、形的前提下，力求荤素、冷热、粗精，干稀巧搭配，荤素搭配，可以激发人的食欲，保持营养平衡，酸碱平衡。就冷热菜肴的口味而言，不仅要求甜、咸巧调理，还应做到麻、辣、酸、苦巧搭配，达到五味俱全，才能胃口大开。粗精搭配，就是要适当吃些粗粮、多纤维食物，帮助消化。干稀搭配，主要是指主食花样多，有米饭、馒头、大饼、蒸包、水饺、元宵、年糕等；粥、汤、甜饮等也要品种变换，品味多样。"),
    Paragraph("肥瘦巧补充"+
        "节日荤食应少而精，肥瘦搭配，以瘦为主。口味要清淡，多选择平时少吃的牛肉、狗肉、兔肉、鸽肉、猪肉、鸡鸭鹅爪等，以及海鲜、鱼类。鱼、肉的制作要以清蒸为主，少用油炸、烧烤。"),
    Paragraph("夜餐巧安排"+
        "节日期间，人们常玩到深夜，甚至通宵达旦，因此免不了吃点夜餐。由于白天饮食丰盛，夜餐吃点馒头、包子、面条或喝点牛奶等热饮即可，既充了饥，又不伤脾胃，还有利于睡眠。"),
    Paragraph("零食巧调理"+
        "节日的三餐主食多以丰厚的油腻食品为主，所以，零食应以开胃、理气、消胀、降火、祛燥为特点，如饭后两小时左右，可吃点苹果、梨、香蕉等，以开胃通便；少吃花生、瓜子、糖果、巧克力等零食；而橘子、柿子等以不吃为好。另外，像话梅、山楂片、薄荷糖等可适度品味。"),
    Paragraph("烟酒巧分开"+
        "喜庆佳节，亲朋好友欢聚，少不了饮酒助兴。适量饮点酒可以促进血液循坏，消除疲劳，加快新陈代谢，但是，切不可畅饮无度，一醉方休。尤其是一边酗酒一边大量吸烟，对身体的摧残相当严重。因此，节日期间应做到酒席桌上不吸烟，饮茶吸烟不喝酒，避免人体肝、胃、肺同时受到侵害。"),
    Paragraph("伤食巧调治"+
        "过节，一些人不注意自己的消化能力，很容易出现伤食。对伤食症可用桂皮、山楂各10克，红糖30克，水煮桂皮、山楂，滤汁放入红糖调匀，热饮。或用槟榔、炒莱菔子、生山楂各10克，水煮三物，去渣，凉后加入白糖少许，以此代开水饮，效果很好。"),
    Paragraph("购物时注意营养标示"+
        "当您决定菜单后，便要开始购买，记得千万不要空腹时去购买，免得买一堆不在菜单上的食物回来，而且最好以新鲜蔬果为主要素材，避免购买点心与速食类的食品，记得一定要仔细阅读包装上营养标示，选择每份食物脂肪热量低于总热量的1/3(一公克脂肪=9大卡热量)，避免购买已处理过的食物，因为这些食物通常含高脂肪量。尽量选择低脂食品取代全脂。这样一来，便可为您的饮食先把第一关，让过多脂肪远离你!"),
    Paragraph("烹调时少油炸"+
        "烹煮食物的器具，以不沾锅较好，因为可以减少食用油的量;同时避免用油炸或煎调理，可以烤或蒸取代，这样可以减少过多的油被吃进肚子里去;也尽量少用酱料及调味料，可以用香料取代;蛋制食物，可以两个蛋白取代全蛋;烹煮前可以将肉类的皮去除，因为皮层通常含有较多脂肪。最后，记得不要准备超过需要的份量，不然就会因为煮的辛苦，而不得不通通吃进去罗!"),
    Paragraph("圣诞健康饮酒小常识"+
        "每个健康的成年人都具有相当不错的酒精分解消化能力，因为人体的肠道细菌于正常情况下，每天约可释出30mL以上的纯酒精。所以，即使是滴酒不沾的人，在自然的情况下，每天也会不知不觉地喝入了相当于250mL葡萄酒或750mL啤酒的酒精含量之自然酒精，人若非天生就具有分解这些酒精的能力，将是每天都醉醺醺的。\n" +
                "由于人体可自然分解消化酒精，故适当饮酒不但不会影响健康甚至有益健康，国外有很多医学研究报告均提出适当饮酒可使动脉血管扩张、血压下降，有利于患轻微高血压及血液循环不良的患者，而适量葡萄酒更能提升有利的高密度脂蛋白及降低有害的低密度脂蛋白，故有利于防止心脏病发作，我国传统的酿酒(绍兴酒、糯米酒)也是自古流传的滋养补品。\n" +
                "必须注意的是饮酒过量会对肝脏造成损害；同时，过量摄食酒精会造成体内乳酸增多而抑制尿酸的排泄，增加尿酸的累积，故痛风病人应避免过量饮酒。")
)

val paragraphPost6 = listOf(
    Paragraph("近期，由于天气转凉，呼吸道疾病病例显著增多。日前，生活报记者采访了哈尔滨市第一医院呼吸内科二病房主任赵庆东和哈市第一医院呼吸内一科主任李建龙，听他们讲述呼吸道疾病的预防及治疗方法。"),
    Paragraph("17岁的小林（化名）是一名在校高中生，每天在校时间常超过9个小时。前天，小林从学校回家后便感到身体不适，起初只是有些轻微头痛和乏力，家人以为是学习累所致，没太在意。然而，到了晚上，小林的体温迅速升高，达到了39.5℃，同时伴有剧烈的咳嗽和高烧不退，家人这才意识到问题的严重性，急忙带他到医院就诊。经过医生详细检查，小林被确诊为急性上呼吸道疾病。"),
    Paragraph("看着孩子在病床上虚弱的样子，小林的父母心急如焚，一刻也不敢离开，轮流在医院照顾。小林的爷爷、奶奶更是担心不已，每天都会到医院来探望，给他带来家里熬制的营养粥和他爱吃的零食，希望能帮助他尽快恢复。然而，就在小林住院治疗几天后，他的家人的身体也出现了状况。先是小林的母亲，在医院照顾小林期间由于长时间近距离接触，且在医院这样的环境中难免有所疏忽，没有做好足够的防护措施，她开始出现发热、咽痛的症状。紧接着，小林的父亲和爷爷、奶奶也相继出现了类似症状，一家五口相继中招。"),
    Paragraph("幸运的是，经过医护人员的精心救治和悉心照料，小林一家人的病情均已得到有效控制，目前已回归正常生活。"),
    Paragraph("哈市第一医院呼吸内一科主任李建龙提醒：秋冬季是呼吸道疾病频发的季节，近期就诊人数从以往日均30余人增至日均70余人。老年人出门活动时，需要佩戴口罩有效隔绝细菌及病毒。同时，保持良好的个人卫生习惯也很重要，包括勤洗手、使用含酒精的手部消毒剂、避免用手触摸眼睛、鼻子和嘴巴等。在打喷嚏或咳嗽时应用手帕或纸巾包好，避免飞沫传污染他人。平时可以多去户外进行锻炼，提高自身的免疫力，一定要注意防护。在饮食方面，保持均衡饮食，多摄入富含维生素和矿物质的食物，比如新鲜的水果、蔬菜、坚果、高蛋白食物。避免过度疲劳，保持充足的睡眠。此外，要注意保持室内空气流通，避免到人群密集且通风不良的场所活动。"),
    Paragraph("哈尔滨市第一医院呼吸内科二病房主任赵庆东告诉记者，随着气温的变化和空气质量的波动，呼吸道疾病的发病率往往会显著增加。呼吸道疾病患者会有咳嗽、有痰、发热等症状，一旦出现疑似症状，应立即佩戴口罩，减少与他人接触，并尽快前往医院就诊。医生会根据病情开具药方，早期治疗可缩短病程，减轻症状。在治疗期间，患者需要多休息，保证充足的睡眠，增加免疫力来对抗病毒。同时，患者要增加饮水量，保持每天1500-2000ml，这样可以保持呼吸道黏膜湿润，有利于痰液排出。"),
    Paragraph("另外，要保持室内空气流通，适宜的温度和湿度也有助于缓解患者的不适症状。对于免疫力相对较弱的群体（如孕妇、低龄儿童、有慢性病病史的老年人等），应根据疫苗接种的适用条件，积极接种预防呼吸道疾病的相关疫苗。")
)


//问题类（选项、问题、问题集）
data class Option(
    val option: String,
    val text: String
)
data class Question(
    val title: String,
    val options: List<Option> = emptyList(),
    val answer: String,
    val reason: String
)
val question1 = Question(
    title = "一次献血的量最好在哪个区间？",
    options = listOf(
        Option(option = "A", text = "100-200ml"),
        Option(option = "B", text = "200-400ml"),
        Option(option = "C", text = "400-800ml")),
    answer = "B",
    reason = ""
)
val question2 = Question(
    title = "以下哪个不是对献血人员的要求？",
    options = listOf(
        Option(option = "A", text = "年龄在18-55周岁"),
        Option(option = "B", text = "体重大于45Kg"),
        Option(option = "C", text = "体重小于200Kg"),
        Option(option = "D", text = "无发热、肿瘤、血液传染病等疾病"),
        Option(option = "E", text = "无高血压、低血压情况")),
    answer = "C",
    reason = "献血对体重有最小要求而没有最大要求"
)
val questions1 = listOf(
    question1,
    question2
)
val results1 = listOf("","")

val question11 = Question(
    title = "在成年以后，尤其____的时候，肥胖人群发生高血压的机会比平常人多50%。",
    options = listOf(
        Option(option = "A", text = "40-50岁"),
        Option(option = "B", text = "10-20岁"),
        Option(option = "C", text = "50岁以上"),
        Option(option = "D", text = "20-30岁")
    ),
    answer = "A",
    reason = ""
)
val question12 = Question(
    title = "控制体重的策略应强调____",
    options = listOf(
        Option(option = "A", text = "行为治疗"),
        Option(option = "B", text = "饮食治疗"),
        Option(option = "C", text = "体育锻炼"),
        Option(option = "D", text = "以行为、饮食治疗为主的综合治疗")
    ),
    answer = "D",
    reason = ""
)
val question13 = Question(
    title = "下列关于肥胖症的说法不正确的是",
    options = listOf(
        Option(option = "A", text = "肥胖症可作为某些疾病的临床表现之一"),
        Option(option = "B", text = "肥胖症患者的预期寿命缩短"),
        Option(option = "C", text = "梨型肥胖症患者发生代谢综合征的危险性大于苹果形肥胖症患者"),
        Option(option = "D", text = "肥胖症患者恶性肿瘤的发生率升高")
    ),
    answer = "C",
    reason = ""
)
val question14 = Question(
    title = "药物减重的目标不包括",
    options = listOf(
        Option(option = "A", text = "使体重恢复正常范围"),
        Option(option = "B", text = "使体重减轻5%~10%"),
        Option(option = "C", text = "最好逐渐接近理想体重"),
        Option(option = "D", text = "减重后维持体重不再反弹和增加")
    ),
    answer = "A",
    reason = ""
)
val question15 = Question(
    title = "肥胖的手术治疗不包括",
    options = listOf(
        Option(option = "A", text = "吸脂术"),
        Option(option = "B", text = "切脂术"),
        Option(option = "C", text = "垂直结扎胃成型手术"),
        Option(option = "D", text = "胃大部分切除手术")
    ),
    answer = "D",
    reason = ""
)
val question16 = Question(
    title = "肥胖的脑力劳动者每天需要的热量是",
    options = listOf(
        Option(option = "A", text = "20cal x 标准体重（kg）"),
        Option(option = "B", text = "25cal x 标准体重（kg）"),
        Option(option = "C", text = "30cal x 标准体重（kg）"),
        Option(option = "D", text = "35cal x 标准体重（kg）")
    ),
    answer = "B",
    reason = ""
)
val question17 = Question(
    title = "继发性肥胖症常见于",
    options = listOf(
        Option(option = "A", text = "肿瘤"),
        Option(option = "B", text = "创伤"),
        Option(option = "C", text = "下丘脑、垂体的炎症"),
        Option(option = "D", text = "以上都是")
    ),
    answer = "D",
    reason = ""
)
val question18 = Question(
    title = "运动最好在饭后几个小时",
    options = listOf(
        Option(option = "A", text = "1h"),
        Option(option = "B", text = "1.5h"),
        Option(option = "C", text = "2h"),
        Option(option = "D", text = "3h")
    ),
    answer = "C",
    reason = ""
)
val question19 = Question(
    title = "膳食中，脂肪提供能量所占比例的适宜范围是",
    options = listOf(
        Option(option = "A", text = "0-10%"),
        Option(option = "B", text = "10-15%"),
        Option(option = "C", text = "15-20%"),
        Option(option = "D", text = "20-30%")
    ),
    answer = "D",
    reason = ""
)
val question110 = Question(
    title = "对于儿童青少年，是好的饮料是",
    options = listOf(
        Option(option = "A", text = "冰红茶"),
        Option(option = "B", text = "雪碧"),
        Option(option = "C", text = "可口可乐"),
        Option(option = "D", text = "白开水")
    ),
    answer = "D",
    reason = ""
)
val questions2 = listOf(
    question11,
    question12,
    question13,
    question14,
    question15,
    question16,
    question17,
    question18,
    question19,
    question110
)

val results2 = listOf("","")

val question21 = Question(
    title = "冬季起床时，中老年人应该遵循的三个“一分钟”是什么？",
    options = listOf(
        Option(option = "A", text = "醒来后立即起床"),
        Option(option = "B", text = "醒来后在床上躺一分钟，坐一分钟，站一分钟"),
        Option(option = "C", text = "醒来后直接走动"),
        Option(option = "D", text = "醒来后在床上躺三分钟")
    ),
    answer = "B",
    reason = ""
)
val question22 = Question(
    title = "冬季晨起和夜间喝水的建议量是多少？",
    options = listOf(
        Option(option = "A", text = "50ml"),
        Option(option = "B", text = "100ml"),
        Option(option = "C", text = "200ml"),
        Option(option = "D", text = "500ml")
    ),
    answer = "C",
    reason = ""
)
val question23 = Question(
    title = "冬季室内保持湿度适中的方法有哪些？",
    options = listOf(
        Option(option = "A", text = "放置加湿器"),
        Option(option = "B", text = "洒水"),
        Option(option = "C", text = "保持窗户紧闭"),
        Option(option = "D", text = "A和B")
    ),
    answer = "D",
    reason = ""
)
val question24 = Question(
    title = "老年人冬季饮食中应该控制的三种物质是什么？",
    options = listOf(
        Option(option = "A", text = "盐、糖、油"),
        Option(option = "B", text = "蛋白质、维生素、纤维素"),
        Option(option = "C", text = "铁、钙、锌"),
        Option(option = "D", text = "水、脂肪、碳水化合物")
    ),
    answer = "A",
    reason = ""
)
val question25 = Question(
    title = "老年人冬季饮食中“四忌”包括哪些？",
    options = listOf(
        Option(option = "A", text = "忌多、忌硬、忌偏食、忌寒"),
        Option(option = "B", text = "忌少、忌软、忌全食、忌热"),
        Option(option = "C", text = "忌多、忌软、忌偏食、忌热"),
        Option(option = "D", text = "忌少、忌硬、忌全食、忌寒")
    ),
    answer = "A",
    reason = ""
)
val question26 = Question(
    title = "冬季老年人进行体育锻炼的目的是什么？",
    options = listOf(
        Option(option = "A", text = "增强体质、防病保健"),
        Option(option = "B", text = "减肥、塑形"),
        Option(option = "C", text = "增加食欲、改善睡眠"),
        Option(option = "D", text = "提高工作效率、减轻压力")
    ),
    answer = "A",
    reason = ""
)
val question27 = Question(
    title = "老年人冬季锻炼时应该选择哪些运动方式？",
    options = listOf(
        Option(option = "A", text = "散步、太极拳、瑜伽"),
        Option(option = "B", text = "长跑、举重、游泳"),
        Option(option = "C", text = "篮球、足球、橄榄球"),
        Option(option = "D", text = "爬山、滑雪、潜水")
    ),
    answer = "A",
    reason = ""
)
val question28 = Question(
    title = "冬季锻炼对老年人的血液循环有什么影响？",
    options = listOf(
        Option(option = "A", text = "促进血液循环"),
        Option(option = "B", text = "减缓血液循环"),
        Option(option = "C", text = "无明显影响"),
        Option(option = "D", text = "导致血液循环紊乱")
    ),
    answer = "A",
    reason = ""
)
val question29 = Question(
    title = "冬季锻炼对老年人呼吸系统功能的影响是什么？",
    options = listOf(
        Option(option = "A", text = "增强呼吸系统功能，改善肺活量和呼吸质量"),
        Option(option = "B", text = "减弱呼吸系统功能，降低肺活量和呼吸质量"),
        Option(option = "C", text = "对呼吸系统功能无影响"),
        Option(option = "D", text = "导致呼吸系统疾病")
    ),
    answer = "A",
    reason = ""
)
val question210 = Question(
    title = "冬季老年人保持身体健康的最终目的是什么？",
    options = listOf(
        Option(option = "A", text = "享受幸福美满的晚年生活"),
        Option(option = "B", text = "提高工作效率"),
        Option(option = "C", text = "增加社交活动"),
        Option(option = "D", text = "改善睡眠质量")
    ),
    answer = "A",
    reason = ""
)
val questions3 = listOf(
    question21,
    question22,
    question23,
    question24,
    question25,
    question26,
    question27,
    question28,
    question29,
    question210
)

val results3 = listOf("","")

//全页面（文章+问题集）
enum class PostType{
    ACTIVITY,
    ARTICLE,
    QUIZ
}
data class Post(
    val id: String,
    val type: PostType,
    val title: String,
    val subtitle: String? = null,
    val metadata: Metadata,
    val paragraphs: List<Paragraph> = emptyList(),
    val questions: List<Question> = emptyList(),
    val results: List<String> = emptyList(),
    @DrawableRes val imageId: Int,
)
val post1 = Post(
    id = "1",
    type = PostType.ARTICLE,
    title = "世界献血日",
    subtitle = "携手共进，让这个世界变得更加美好",
    metadata = Metadata(
        author = sakiko,
        date = "12月18日",
        readTimeMinutes = 1
    ),
    paragraphs = paragraphsPost1,
    questions = questions1,
    results = results1,
    imageId = R.drawable.sakiko,
)
val post2 = Post(
    id = "2",
    type = PostType.ACTIVITY,
    title = "校医院邀请南京脑科专家来校义诊",
    subtitle = "十一月份”名医进校园”系列活动",
    metadata = Metadata(
        author = hospital,
        date = "11月10日",
        readTimeMinutes = 1
    ),
    paragraphs = paragraphsPost2,
    imageId = R.drawable.location,
)
val post3 = Post(
    id="3",
    type = PostType.ARTICLE,
    title="世界防治肥胖日：中国9000万肥胖人群如何科学有效减重？",
    subtitle="管住嘴，迈开腿，说起来容易，做起来难",
    metadata = Metadata(
        author = health,
        date = "12月31日",
        readTimeMinutes = 1
    ),
    paragraphs = paragraphPost3,
    questions = questions2,
    results = results2,
    imageId = R.drawable.health,
)
val post4 = Post(
    id="4",
    type = PostType.ARTICLE,
    title="冬季养生：老年人如何科学健康度过寒冷的冬季？",
    subtitle="冬季养生，老年人如何科学健康度过寒冷的冬季？",
    metadata = Metadata(
        author = health,
        date = "12月31日",
        readTimeMinutes = 1
    ),
    paragraphs = paragraphPost4,
    questions = questions3,
    results = results3,
    imageId = R.drawable.health,
)

val post5= Post(
    id="5",
    type = PostType.ARTICLE,
    title="“圣诞节”饮食健康有禁忌，早知早防，开心过圣诞",
    subtitle="“佳肴满桌，欢声满堂，愿健康与喜悦同在，共度温馨圣诞。”",
    metadata = Metadata(
        author = health,
        date = "12月24日",
        readTimeMinutes = 4
    ),
    paragraphs = paragraphPost5,
    imageId = R.drawable.health,
)
val post6= Post(
    id="6",
    type = PostType.ARTICLE,
    title="冬季呼吸道疾病预防及治疗",
    subtitle="呼吸道疾病的预防及治疗方法",
    metadata = Metadata(
        author = health,
        date = "12月27日",
        readTimeMinutes = 3
    ),
    paragraphs = paragraphPost6,
    imageId = R.drawable.health,
)
