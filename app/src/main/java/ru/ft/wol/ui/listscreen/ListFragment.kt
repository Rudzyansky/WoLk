package ru.ft.wol.ui.listscreen

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ft.wol.databinding.FragmentListBinding
import ru.ft.wol.ui.base.BaseFragment

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val listViewModel by viewModel<ListViewModel>()
    private val wakeUpViewModel by viewModel<WakeUpViewModel>()
    private val clientsAdapter = ClientsAdapter()
    private var toast: Toast? = null

    override fun onPause() {
        super.onPause()
        toast?.cancel()
    }

    override fun setup() {
        listViewModel.clients
            .onEach(clientsAdapter::submitList)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.clients.adapter = clientsAdapter
        clientsAdapter.setOnWakeUpClickListener {
            wakeUpViewModel.wakeUp(it)
            toast?.cancel()
            toast = Toast.makeText(context, "WoL packet sent to ${it.name}", Toast.LENGTH_SHORT)
            toast?.show()
        }
        clientsAdapter.setOnLongClickListener {
            val editFragment = ListFragmentDirections.actionListFragmentToEditFragment()
            editFragment.client = it
            findNavController().navigate(editFragment)
        }
        binding.add.setOnClickListener {
            val editFragment = ListFragmentDirections.actionListFragmentToEditFragment()
            findNavController().navigate(editFragment)
        }
    }
}