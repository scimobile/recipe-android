import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel
import com.sci.recipeandroid.feature.personalize.domain.repository.PersonalizeRepository
import com.sci.recipeandroid.util.SingleLiveEvent
import kotlinx.coroutines.launch

class PersonalizeGoalsViewModel(
    private val personalizeRepository: PersonalizeRepository
) : ViewModel() {


    val uiEvent = SingleLiveEvent<PersonalizeGoalsUiEvent>()

    private val _uiState = MutableLiveData<PersonalizeGoalsUiState>()
    val uiState: LiveData<PersonalizeGoalsUiState>
        get() = _uiState

    private val _selectedGoal = MutableLiveData<PersonalizeGoalsModel?>()
    val selectedGoal: LiveData<PersonalizeGoalsModel?>
        get() = _selectedGoal

    init {

        _uiState.value = PersonalizeGoalsUiState.Loading
        viewModelScope.launch {
            personalizeRepository.getPersonalizeGoalsList()
                .fold(
                    onSuccess = {
                        _uiState.value = PersonalizeGoalsUiState.Success(it)
                    },
                    onFailure = {
                        uiEvent.value = PersonalizeGoalsUiEvent.Error(
                            icon = "",
                            message = it.message.toString()
                        )
                    }
                )
        }
    }

    fun selectGoal(goal: PersonalizeGoalsModel) {
        _selectedGoal.value = goal
    }
}

sealed class PersonalizeGoalsUiState {
    data object Loading : PersonalizeGoalsUiState()
    data class Success(val personalizeGoalsList: List<PersonalizeGoalsModel>) : PersonalizeGoalsUiState()
}

sealed class PersonalizeGoalsUiEvent {
    data class Error(
        val icon: String,
        val message: String,
    ) : PersonalizeGoalsUiEvent()
}
