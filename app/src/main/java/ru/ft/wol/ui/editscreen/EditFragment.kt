package ru.ft.wol.ui.editscreen

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.ft.wol.databinding.FragmentEditBinding
import ru.ft.wol.domain.entity.Client
import ru.ft.wol.ui.base.BaseFragment

class EditFragment : BaseFragment<FragmentEditBinding>(FragmentEditBinding::inflate) {

    private val args by navArgs<EditFragmentArgs>()

    private val vmFactory by inject<EditViewModel.Factory>()
    private val viewModel by viewModels<EditViewModel> {
        EditViewModel.factory(vmFactory, args.client)
    }

    override fun setup() {
        viewModel.client
            .onEach(::onClientDataChanged)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.save.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.storeClient(
                    name = binding.name.text.toString(),
                    address = binding.address.text.toString(),
                    macAddress = binding.macAddress.text.toString()
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun onClientDataChanged(client: Client) = with(binding) {
        name.setText(client.name)
        address.setText(client.address)
        macAddress.setText(client.macAddress)
    }
}