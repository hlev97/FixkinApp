package hu.bme.aut.it9p0z.ui.data

import hu.bme.aut.it9p0z.ui.R
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText

sealed class Medicines(val name: UiText) {
    object Corticosteroids :
        Medicines(name = UiText.StringResource(R.string.medicines_name_corticosteroids))

    object VitaminDAnalogues :
        Medicines(name = UiText.StringResource(R.string.medicines_name_vitamin_d_analogues))

    object Retinoids :
        Medicines(name = UiText.StringResource(R.string.medicines_name_retinoids))

    object CalcineurinInhibitors :
        Medicines(name = UiText.StringResource(R.string.medicines_name_calcineurin_inhibitors))

    object SalicylicAcid :
        Medicines(name = UiText.StringResource(R.string.medicines_name_salicylic_acid))

    object CoalTar :
        Medicines(name = UiText.StringResource(R.string.coal_tar))

    object Anthralin :
        Medicines(name = UiText.StringResource(R.string.medicines_name_anthralin))

    object Sunlight :
        Medicines(name = UiText.StringResource(R.string.medicines_name_sunlight))

    object UVBBroadband :
        Medicines(name = UiText.StringResource(R.string.medicines_name_uvb_broadband))

    object UVBNarrowband :
        Medicines(name = UiText.StringResource(R.string.medicines_name_uvb_narrowband))

    object Steroids :
        Medicines(name = UiText.StringResource(R.string.medicines_name_steroids))

    object RetinoidPills :
        Medicines(name = UiText.StringResource(R.string.medicines_name_retinoid_pills))

    object Biologics :
        Medicines(name = UiText.StringResource(R.string.medicines_name_biologics))

    object Methotrexate :
        Medicines(name = UiText.StringResource(R.string.medicines_name_methotrexate))

    object Cyclosporine :
        Medicines(name = UiText.StringResource(R.string.medicines_name_cyclosporine))

    object Supliments :
        Medicines(name = UiText.StringResource(R.string.medicines_name_supliments))

    companion object {

        private val topical = listOf(
            Corticosteroids,
            VitaminDAnalogues,
            Retinoids,
            CalcineurinInhibitors,
            SalicylicAcid,
            CoalTar,
            Anthralin,
        )

        val topicalUiChips = topical.map { UiChip(label = it.name) }

        private val oralOrInjected = listOf(
            Steroids,
            RetinoidPills,
            Biologics,
            Methotrexate,
            Cyclosporine,
        )

        val oralOrInjectedUiChips = oralOrInjected.map { UiChip(label = it.name) }

        private val other = listOf(
            Sunlight,
            UVBNarrowband,
            UVBBroadband,
            Supliments
        )

        val otherUiChips = other.map { UiChip(label = it.name) }
    }

}
