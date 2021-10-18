package com.fghilmany.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.fghilmany.myapplication.databinding.DialogAddItemBinding
import java.util.*

class DialogAddItemFragment : DialogFragment() {

    private var _binding: DialogAddItemBinding? = null
    private val binding get() = _binding!!

    private var isEdit = false
    private var data: Data? = null
    private var position: Int? = null

    companion object{
        val TAG: String = this::class.java.simpleName

        private const val IS_EDIT = "is_edit"
        private const val DATA = "data"
        private const val POSITION = "position"

        fun newInstance(isEdit: Boolean, data: Data? = null, position: Int? = null): DialogAddItemFragment {
            val args = Bundle()
            args.putBoolean(IS_EDIT, isEdit)
            args.putParcelable(DATA, data)
            if (position != null) {
                args.putInt(POSITION, position)
            }
            val fragment = DialogAddItemFragment()
            fragment.arguments = args
            return fragment
        }

        var listener: Listener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DialogAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isEdit = arguments?.getBoolean(IS_EDIT)?:false
        data = arguments?.getParcelable(DATA)
        position = arguments?.getInt(POSITION)

        with(binding){

            if (isEdit){
                btAddItem.text = "Edit Item"
                swCustomPosition.visibility = View.GONE
                tilCustomPosition.visibility = View.VISIBLE

                etName.setText(data?.name)
                etDesc.setText(data?.desc)
                etCustomPosition.setText((position?:0 + 1).toString())
            }

            swCustomPosition.setOnCheckedChangeListener { _, b ->
                if (b)
                    tilCustomPosition.visibility = View.VISIBLE
                else
                    tilCustomPosition.visibility = View.GONE
            }


            btAddItem.setOnClickListener {

                val id = UUID.randomUUID().toString()
                val name = etName.text.toString()
                val desc = etDesc.text.toString()

                if (isEdit){
                    val data = Data(
                        data?.id, name, desc
                    )

                    val isMove = position?:0 != etCustomPosition.text.toString().toInt()
                    position = etCustomPosition.text.toString().toInt()
                    listener?.onEditItem(data, position, isMove)
                }else{
                    val data = Data(
                        id, name, desc
                    )

                    val getPosition: Int? = if (etCustomPosition.text.toString().isBlank()) null else etCustomPosition.text.toString().toInt()
                    position = if (tilCustomPosition.isVisible) getPosition else null
                    listener?.onAddItem(data, position)
                }


                dismiss()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }


}