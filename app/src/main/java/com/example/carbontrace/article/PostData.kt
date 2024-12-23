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




