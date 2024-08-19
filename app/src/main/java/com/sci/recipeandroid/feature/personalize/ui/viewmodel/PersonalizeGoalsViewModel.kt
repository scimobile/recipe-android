import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepository
import com.sci.recipeandroid.feature.personalize.ui.model.PersonalizeGoalsUiModel
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.launch

class PersonalizeGoalsViewModel(
    private val personalizeRepository: PersonalizeRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<PersonalizeGoalsUiState>()
    val uiState: LiveData<PersonalizeGoalsUiState> = _uiState
    var personalizeGoalsUiModel: List<PersonalizeGoalsUiModel> = emptyList()

    private val _selectedItem = MutableLiveData<PersonalizeGoalsUiModel?>()
    val selectedItem: LiveData<PersonalizeGoalsUiModel?>
        get() = _selectedItem

    val uiEvent = SingleLiveEvent<PersonalizeGoalsUiEvent>()

    init {
        viewModelScope.launch {
            _uiState.postValue(PersonalizeGoalsUiState.Loading)
            val result = personalizeRepository.getPersonalizeGoalsList()
            result.onSuccess { items ->
                personalizeGoalsUiModel = items.map {
                    PersonalizeGoalsUiModel(personalizeGoalsModel = it)
                }
            }
            _uiState.postValue(PersonalizeGoalsUiState.Success(personalizeGoalsUiModel))
        }
    }

    fun selectItem(id: String?) {
        var selectedItem: PersonalizeGoalsUiModel? = null
        personalizeGoalsUiModel = personalizeGoalsUiModel.map { item ->
            if (item.personalizeGoalsModel.id == id) {
                selectedItem = item.copy(isSelect = true)
                selectedItem!!
            } else {
                item.copy(isSelect = false)
            }
        }
        _selectedItem.value = selectedItem
        _uiState.postValue(
            PersonalizeGoalsUiState.UpdatePersonalizeGoalsList(personalizeGoalsUiModel)
        )
    }
}

sealed class PersonalizeGoalsUiState {
    data object Loading : PersonalizeGoalsUiState()
    data class Success(val personalizeGoalsList: List<PersonalizeGoalsUiModel>) : PersonalizeGoalsUiState()
    data class UpdatePersonalizeGoalsList(val items: List<PersonalizeGoalsUiModel>) : PersonalizeGoalsUiState()
}

sealed class PersonalizeGoalsUiEvent {
    data class Error(
        val icon: String,
        val message: String,
    ) : PersonalizeGoalsUiEvent()
}
