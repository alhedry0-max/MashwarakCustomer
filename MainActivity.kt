package com.mashwarak.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Gold = Color(0xFFFFB300)
private val Dark = Color(0xFF171717)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { MashwarakApp() } }
}

@Composable fun MashwarakApp() {
    var screen by remember { mutableStateOf("home") }
    MaterialTheme(colorScheme = lightColorScheme(primary = Gold, onPrimary = Dark)) {
        when(screen) {
            "home" -> HomeScreen(onTaxi={screen="taxi"}, onOrder={screen="order"})
            "taxi" -> BookingScreen("طلب تكسي", "🚕", "اختر موقع الانطلاق والوجهة", onBack={screen="home"})
            "order" -> BookingScreen("نقل طلبية", "📦", "حدد الاستلام والتسليم", onBack={screen="home"})
        }
    }
}

@Composable fun HomeScreen(onTaxi:()->Unit, onOrder:()->Unit) {
    Scaffold(bottomBar={NavigationBar { NavigationBarItem(true,{},{},label={Text("الرئيسية")}); NavigationBarItem(false,{},{},label={Text("طلباتي")}); NavigationBarItem(false,{},{},label={Text("حسابي")}) }}) { p ->
        LazyColumn(modifier=Modifier.fillMaxSize().padding(p).padding(20.dp), verticalArrangement=Arrangement.spacedBy(16.dp)) {
            item { Text("تكسي مشوارك", fontSize=30.sp, fontWeight=FontWeight.Bold); Text("معانا أريح", color=Gold, fontSize=20.sp, fontWeight=FontWeight.Bold) }
            item { Card(colors=CardDefaults.cardColors(containerColor=Color(0xFFFFF8E1)), modifier=Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp)) { Text("أهلاً بك 👋", fontSize=22.sp, fontWeight=FontWeight.Bold); Text("إلى أين تريد الذهاب؟", modifier=Modifier.padding(top=6.dp)) } } }
            item { ServiceCard("🚕", "طلب تكسي", "احجز سيارة إلى أي مكان", onTaxi) }
            item { ServiceCard("📦", "نقل طلبية", "استلام وتسليم داخل نينوى", onOrder) }
            item { Text("خدماتك الأخيرة", fontSize=20.sp, fontWeight=FontWeight.Bold); Text("لا توجد طلبات حتى الآن", color=Color.Gray) }
        }
    }
}

@Composable fun ServiceCard(icon:String,title:String,desc:String,onClick:()->Unit) { Card(Modifier.fillMaxWidth().clickable{onClick()}, elevation=CardDefaults.cardElevation(3.dp)) { Row(Modifier.padding(18.dp), verticalAlignment=Alignment.CenterVertically) { Text(icon,fontSize=38.sp); Spacer(Modifier.width(16.dp)); Column(Modifier.weight(1f)){Text(title,fontSize=21.sp,fontWeight=FontWeight.Bold);Text(desc,color=Color.Gray)}; Icon(Icons.Default.ChevronLeft,null) } } }

@Composable fun BookingScreen(title:String,icon:String,hint:String,onBack:()->Unit) {
    var from by remember { mutableStateOf("") }; var to by remember { mutableStateOf("") }; var pay by remember { mutableStateOf("نقدي") }
    Scaffold(topBar={TopAppBar(title={Text(title)}, navigationIcon={IconButton(onClick=onBack){Icon(Icons.Default.ArrowForward,null)}})}) { p ->
        Column(Modifier.fillMaxSize().padding(p).padding(20.dp), verticalArrangement=Arrangement.spacedBy(14.dp)) {
            Text(icon,fontSize=48.sp); Text(hint,fontSize=20.sp,fontWeight=FontWeight.Bold)
            OutlinedTextField(from,{from=it},Modifier.fillMaxWidth(),label={Text("موقع الاستلام")},leadingIcon={Icon(Icons.Default.LocationOn,null)})
            OutlinedTextField(to,{to=it},Modifier.fillMaxWidth(),label={Text("موقع التسليم")},leadingIcon={Icon(Icons.Default.Place,null)})
            Text("طريقة الدفع",fontWeight=FontWeight.Bold)
            Row(horizontalArrangement=Arrangement.spacedBy(10.dp)){ listOf("نقدي","إلكتروني").forEach{ x -> FilterChip(selected=pay==x,onClick={pay=x},label={Text(x)},leadingIcon={Icon(if(x=="نقدي") Icons.Default.Payments else Icons.Default.CreditCard,null)}) } }
            Card(Modifier.fillMaxWidth(), colors=CardDefaults.cardColors(containerColor=Color(0xFFF5F5F5))) { Column(Modifier.padding(16.dp)){Text("السعر يُحسب تلقائيًا",fontWeight=FontWeight.Bold);Text("سيتم تحديد الأجرة حسب المسافة ونوع الخدمة. الأسعار قابلة للتغيير من الإدارة.",color=Color.Gray)} }
            Spacer(Modifier.weight(1f)); Button(onClick={},Modifier.fillMaxWidth().height(54.dp)){Text("تأكيد الطلب",fontSize=18.sp)}
        }
    }
}
