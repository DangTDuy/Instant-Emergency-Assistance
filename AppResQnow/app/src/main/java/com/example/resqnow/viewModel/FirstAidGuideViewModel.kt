    package com.example.resqnow.viewModel


    import android.R.attr.title
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import androidx.lifecycle.viewModelScope
    import com.example.resqnow.Repository.Repository
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.launch

    data class FirstAidGuide(val title: String)
    class FirstAidGuideViewModel : ViewModel() {
        // Danh sách hướng dẫn sơ cứu tĩnh
        private val staticGuides = listOf(
                    FirstAidGuide(title = "Gãy tay"),
                    FirstAidGuide(title = "Đột quỵ"),
                    FirstAidGuide(title = "Nuốt chất đọc"),
                    FirstAidGuide(title = "Rắn cắn (có độc)"),
                    FirstAidGuide(title = "Rắn cắn (không độc)"),
                    FirstAidGuide(title = "Bỏng nhiệt"),
                    FirstAidGuide(title = "Gân,Cơ,Bầm"),
                    FirstAidGuide(title = "Gãy xương (hở)"),
                    FirstAidGuide(title = "Vết cắt sâu ở chi"),
                    FirstAidGuide(title = "Vết đâm xuyên"),
                    FirstAidGuide(title = "Đứt lìa ngón"),
                    FirstAidGuide(title = "Chảy máu mũi"),


        )

        // Lưu danh sách hướng dẫn trong _guides
        private val _guides = MutableStateFlow(staticGuides)
        //Cập nhật laại danh sách
        val guides: StateFlow<List<FirstAidGuide>> = _guides

        // Hàm tìm kiếm theo query
        fun searchGuides(query: String) {
                _guides.value = staticGuides.filter {
                    it.title.contains(query, ignoreCase = true)
                }
        }
    }


