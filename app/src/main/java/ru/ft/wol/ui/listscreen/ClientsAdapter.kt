package ru.ft.wol.ui.listscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.ft.wol.databinding.ItemClientBinding
import ru.ft.wol.domain.entity.Client

class ClientsAdapter : ListAdapter<Client, ClientViewHolder>(ClientDiffCallback()) {

    private var onWakeUpClickListener: ((client: Client) -> Unit)? = null
    fun setOnWakeUpClickListener(l: (client: Client) -> Unit) {
        onWakeUpClickListener = l
    }

    private var onLongClickListener: ((client: Client) -> Unit)? = null
    fun setOnLongClickListener(l: (client: Client) -> Unit) {
        onLongClickListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClientBinding.inflate(inflater, parent, false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = getItem(position)
        with(holder.binding) {
            name.text = client.name
            address.text = client.address
            macAddress.text = client.macAddress
            wakeUp.setOnClickListener { onWakeUpClickListener?.invoke(client) }
            root.setOnLongClickListener {
                onLongClickListener?.invoke(client)
                true
            }

            name.visibility = View.VISIBLE
            address.visibility = View.INVISIBLE
            macAddress.visibility = View.INVISIBLE

            var animation: ViewPropertyAnimator? = null
            root.setOnClickListener {
                animation?.cancel()
                animation = root.animate()
                    .scaleY(0f)
                    .scaleX(1.005f)
                    .translationZ(5f)
                    .setDuration(100)
                    .withEndAction {
                        if (name.visibility == View.VISIBLE) {
                            name.visibility = View.INVISIBLE
                            address.visibility = View.VISIBLE
                            macAddress.visibility = View.VISIBLE
                        } else {
                            name.visibility = View.VISIBLE
                            address.visibility = View.INVISIBLE
                            macAddress.visibility = View.INVISIBLE
                        }
                        animation = root.animate()
                            .scaleY(1f)
                            .scaleX(1f)
                            .translationZ(0f)
                            .setDuration(100)
                        animation?.start()
                    }
                animation?.start()
            }
        }
    }
}

class ClientViewHolder(val binding: ItemClientBinding) : ViewHolder(binding.root)
class ClientDiffCallback : DiffUtil.ItemCallback<Client>() {
    override fun areItemsTheSame(oldItem: Client, newItem: Client) = oldItem === newItem
    override fun areContentsTheSame(oldItem: Client, newItem: Client) = oldItem == newItem
}