package com.example.carbontrace.article

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.carbontrace.ui.theme.carbontraceTheme
import com.example.carbontrace.R

val defaultSpacerSize = 24.dp

//LazyColumn本体
@Composable
fun PostContent(
    post: Post,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState()
) {
    //保存选择状态及判断结果状态
    var checkedOptions = mutableListOf<String>().apply{
        repeat(post.questions.size){ this.add("") }
    }
    var results = remember { post.results.toMutableStateList() }
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = defaultSpacerSize),
        state = state,
    ) {
        postContentItems(post, checkedOptions, results)
    }
}

//依次生成LazyColumn中的各个组件：头图+分隔+主标题+分隔+副标题+分割+作者信息+文章本体+分割+问题
fun LazyListScope.postContentItems(post: Post, checkedOptions: MutableList<String>, results: MutableList<String>) {
    item {
        Spacer(Modifier.height(defaultSpacerSize))
        PostHeaderImage(post)
        Spacer(Modifier.height(defaultSpacerSize))
        Text(post.title, style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(8.dp))
        if (post.subtitle != null) {
            Text(post.subtitle, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(defaultSpacerSize))
        }
    }
    item { PostMetadata(post.metadata, Modifier.padding(bottom = 24.dp)) }
    items(post.paragraphs) { Paragraph(paragraph = it) }
    item { Spacer(Modifier.height(defaultSpacerSize)) }

    item { PostQuestions(post.questions, checkedOptions, results) }
}


//显示头图：宽度最大，高180，居中剪裁
@Composable
private fun PostHeaderImage(post: Post) {
    val imageModifier = Modifier
        .height(180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.large)
    Image(
        painter = painterResource(post.imageId),
        contentDescription = null, // decorative
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    )
}

//显示文章信息：作者头像、作者姓名、上传时间与预期阅读时间
@Composable
private fun PostMetadata(
    metadata: Metadata,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {}
    ) {
        //显示作者头像，适应大小
        Image(
            painter = painterResource(metadata.author.imageId) ,
            contentDescription = null, // decorative
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = metadata.author.name,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "${metadata.date} 预计阅读时间${metadata.readTimeMinutes}分钟",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//显示段落
@Composable
private fun Paragraph(paragraph: Paragraph) {
    Box(modifier = Modifier) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = paragraph.text,
        )
    }
}

//显示问题
@Composable
fun postQuestion(question: Question, index: Int, result: String): String {
    var checkedOption by remember { mutableStateOf("") }
    Column {
        HorizontalDivider(thickness = 2.dp)
        Spacer(Modifier.height(defaultSpacerSize/2))
        Text(text = "Q${index+1}.${question.title}",
            modifier = Modifier.padding(defaultSpacerSize/2))
        question.options.forEach{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = defaultSpacerSize/2)
            ) {
                Text("${it.option} ${it.text}")
                Checkbox(
                    checked =
                        if(result != "")
                            it.option == question.answer || it.option == checkedOption
                        else
                            it.option == checkedOption,
                    colors =
                        if(result != "" && it.option == question.answer)
                            CheckboxDefaults.colors(checkedColor = Color(0xFF008000))
                        else if(result == "wrong" && it.option != question.answer)
                            CheckboxDefaults.colors(checkedColor = Color(0xFFFF0400))
                        else
                            CheckboxDefaults.colors(),
                    onCheckedChange = { isChecked ->
                        if(result == "") {
                            checkedOption = if(isChecked) it.option else ""
                        }
                    }
                )
            }
        }
        Spacer(Modifier.height(defaultSpacerSize/2))
    }
    return checkedOption
}

//显示问题列表和提交表单按键
//只有当所有题目都进行了选择时才能提交答案，只有一次机会
//按键可以设置数据传输逻辑，将答题情况以及得分情况传回服务器
@Composable
fun PostQuestions(questions: List<Question>, checkedOptions: MutableList<String>, results: MutableList<String>){
    questions.forEach{
        val index = questions.indexOf(it)
        checkedOptions[index] = postQuestion(question = it, index = index, result = results[index])
    }
    HorizontalDivider(thickness = 2.dp)
    Spacer(Modifier.height(defaultSpacerSize))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        if(questions.isNotEmpty()) {
            OutlinedButton(
                onClick = {
                    if (checkedOptions.all { it != "" })
                        questions.forEach {
                            val index = questions.indexOf(it)
                            if (it.answer == checkedOptions[index]) {
                                results[index] = "correct"
                            } else {
                                results[index] = "wrong"
                            }
                        }
                }
            ) {
                if (results.all { it != "" }) Text("答案已提交：错误个数为 ${results.count { it == "wrong" }}")
                else if (checkedOptions.all { it != "" }) Text("点击提交答案")
                else Text("尚未完成作答")
            }
        }
    }
    Spacer(Modifier.height(defaultSpacerSize))
}
