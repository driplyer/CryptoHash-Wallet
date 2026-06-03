package com.driplyer.cryptohash.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.driplyer.cryptohash.R

val Inter = FontFamily(
    Font(R.font.inter_18pt_regular, FontWeight.Normal),
    Font(R.font.inter_18pt_medium, FontWeight.Medium),
    Font(R.font.inter_18pt_bold, FontWeight.Bold)
)

data class CryptoAsset(
    val ticker: String,
    val name: String,
    val balanceOrPrice: String,
    val fiatValue: String,
    val rateChange: String,
    val accentColor: Color
)

@Preview
@Composable
fun MainWalletScreen() {
    var currentTab by remember { mutableStateOf("Home") }

    Scaffold(
        containerColor = Color(0xFFF3F4F6),
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val tabs = listOf(
                    Triple("Home", Icons.Default.Home, "Home"),
                    Triple("Analytics", Icons.Default.BarChart, "Analytics"),
                    Triple("Market", Icons.Default.ShowChart, "Market")
                )
                tabs.forEach { (tabId, icon, label) ->
                    NavigationBarItem(
                        selected = currentTab == tabId,
                        onClick = { currentTab = tabId },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label, fontFamily = Inter) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color(0xFF0C0D0E),
                            indicatorColor = Color(0xFF0C0D0E),
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(48.dp).clip(CircleShape).background(Color(0xD3E5E7EB)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("M", fontSize = 22.sp, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Welcome back,", fontFamily = Inter, fontSize = 12.sp, color = Color.Gray)
                        Text("Mike Tyson", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0C0D0E))
                    }
                    IconButton(onClick = {}) { Icon(Icons.Default.Search, "Search", tint = Color.Black) }
                    IconButton(onClick = {}) { Icon(Icons.Default.NotificationsNone, "Alerts", tint = Color.Black) }
                }
            }

            item {
                Crossfade(targetState = currentTab, label = "ScreenCrossfade") { tab ->
                    when (tab) {
                        "Home" -> HomeDashboardLayout()
                        "Analytics" -> AnalyticsDashboardLayout()
                        "Market" -> MarketDashboardLayout()
                    }
                }
            }
        }
    }
}

@Composable
fun HomeDashboardLayout() {
    val portfolioItems = remember {
        listOf(
            CryptoAsset("BTC", "Bitcoin", "0.68", "$72,839.07", "+ 2.14%", Color(0xFFF7931A)),
            CryptoAsset("ETH", "Ethereum", "7.26", "$20,902.92", "+ 2.14%", Color(0xFF627EEA))
        )
    }
    val watchlistItems = remember {
        listOf(
            CryptoAsset("BNB", "Binance Coin", "$672.90", "", "+ 2.14%", Color(0xFFF3BA2F)),
            CryptoAsset("USDT", "Tether", "$1.00", "", "0.00%", Color(0xFF26A17B))
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            shape = ShapeDefaults.Large,
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0C0D0E))
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Total Balance", fontFamily = Inter, fontSize = 13.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(6.dp))
                Text("$72,121.93", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 34.sp, color = Color.White)
                Spacer(modifier = Modifier.height(6.dp))
                Text("↗ +$726.08 total", fontFamily = Inter, fontSize = 13.sp, color = Color(0xFF4CAF50))
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(0.15f),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.ArrowUpward, null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send", fontFamily = Inter)
                    }
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.ArrowDownward, null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Receive", fontFamily = Inter)
                    }
                }
            }
        }

        SectionHeader("Portfolio") { Text("View All", fontSize = 13.sp, color = Color.Gray, modifier = Modifier.clickable {}) }

        LazyRow(contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(portfolioItems) { asset -> CryptoCard(asset) }
        }

        SectionHeader("Watchlist") { Icon(Icons.Default.Add, "Add Asset", tint = Color.Black, modifier = Modifier.clickable {}) }

        Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            watchlistItems.forEach { asset -> WatchlistItem(asset) }
        }
    }
}

@Composable
fun AnalyticsDashboardLayout() {
    val analyticsItems = remember {
        listOf(
            Triple("Crypto Investments", "$1,450.00", Color(0xFFF7931A)),
            Triple("Trading Fees", "$45.20", Color(0xFF627EEA)),
            Triple("Staking Deposits", "$500.00", Color(0xFF26A17B))
        )
    }

    Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Large,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Net Growth (Monthly)", fontFamily = Inter, fontSize = 13.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text("+14.82%", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color(0xFF4CAF50))
                    Text("+$4,120.50", fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color.Gray)
                }
            }
        }

        Text("Activity Breakdown", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            analyticsItems.forEach { (category, amount, color) ->
                Row(
                    modifier = Modifier.fillMaxWidth().clip(ShapeDefaults.Medium).background(Color.White).padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(color))
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(category, fontFamily = Inter, fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color.Black, modifier = Modifier.weight(1f))
                    Text(amount, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun MarketDashboardLayout() {
    val marketCoins = remember {
        listOf(
            CryptoAsset("BTC", "Bitcoin", "$94,320.00", "", "+5.40%", Color(0xFFF7931A)),
            CryptoAsset("ETH", "Ethereum", "$3,110.50", "", "-1.25%", Color(0xFF627EEA)),
            CryptoAsset("SOL", "Solana", "$185.20", "", "+12.10%", Color(0xFF00FFA3)),
            CryptoAsset("ADA", "Cardano", "$0.65", "", "+0.15%", Color(0xFF0033AD))
        )
    }

    Column(modifier = Modifier.padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Live Market Prices", fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            marketCoins.forEach { coin ->
                Row(
                    modifier = Modifier.fillMaxWidth().clip(ShapeDefaults.Medium).background(Color.White).padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssetAvatar(coin)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(coin.ticker, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                        Text(coin.name, fontFamily = Inter, fontSize = 12.sp, color = Color.Gray)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(coin.balanceOrPrice, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                        val isPositive = coin.rateChange.startsWith("+")
                        Text(
                            text = coin.rateChange,
                            fontFamily = Inter,
                            fontSize = 12.sp,
                            color = if (isPositive) Color(0xFF4CAF50) else Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, trailingContent: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
        trailingContent()
    }
}

@Composable
fun CryptoCard(asset: CryptoAsset) {
    Card(
        modifier = Modifier.size(165.dp, 170.dp),
        shape = ShapeDefaults.Large,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AssetAvatar(asset)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(asset.ticker, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                    Text(asset.name, fontFamily = Inter, fontSize = 11.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(asset.balanceOrPrice, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(asset.fiatValue, fontFamily = Inter, fontSize = 11.sp, color = Color.Gray)
                Text(asset.rateChange, fontFamily = Inter, fontSize = 11.sp, color = Color(0xFF4CAF50))
            }
        }
    }
}

@Composable
fun WatchlistItem(asset: CryptoAsset) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(ShapeDefaults.Medium).background(Color.White).padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AssetAvatar(asset)
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(asset.ticker, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
            Text(asset.name, fontFamily = Inter, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(asset.balanceOrPrice, fontFamily = Inter, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
            Text(asset.rateChange, fontFamily = Inter, fontSize = 12.sp, color = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun AssetAvatar(asset: CryptoAsset) {
    Box(
        modifier = Modifier.size(40.dp).clip(CircleShape).background(asset.accentColor.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Text(asset.ticker.take(1), color = asset.accentColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}