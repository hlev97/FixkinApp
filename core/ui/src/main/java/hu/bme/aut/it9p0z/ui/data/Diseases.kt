package hu.bme.aut.it9p0z.ui.data

import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class Disease(val name: UiText) {
    object PlaquePsoriasis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_psoriasis_plaque))

    object NailPsoriasis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_psoriasis_nail))

    object GuttatePsoriasis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_psoriasis_guttate))

    object InversePsoriasis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_psoriasis_inverse))

    object PustularPsoriasis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_psoriasis_pustular))

    object AtopicDermatitis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_atopic_dermatitis))

    object ContactDermatitis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_contact_dermatitis))

    object DyshidroticEczema :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_dyshidrotic))

    object HandEczema :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_hand))

    object Neurodermatitis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_neurodermatitis))

    object NummularEczema :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_nummular))

    object StasisDermatitis :
        Disease(name = UiText.StringResource(id = R.string.diseases_name_eczema_stasis_dermatitis))

    companion object {
        private val psoriasisDiseases = listOf(
            PlaquePsoriasis,
            NailPsoriasis,
            GuttatePsoriasis,
            InversePsoriasis,
            PustularPsoriasis,
        )

        val psoriasisDiseaseUiChips = psoriasisDiseases.map { UiChip(label = it.name) }

        private val eczemaDiseases = listOf(
            AtopicDermatitis,
            ContactDermatitis,
            DyshidroticEczema,
            HandEczema,
            Neurodermatitis,
            NummularEczema,
            StasisDermatitis,
        )

        val eczemaDiseaseUiChips = eczemaDiseases.map { UiChip(label = it.name) }
    }
}