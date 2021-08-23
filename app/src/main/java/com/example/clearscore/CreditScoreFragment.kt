package com.example.clearscore

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import app.futured.donut.DonutSection
import com.example.clearscore.databinding.FragmentCreditScoreBinding
import com.example.clearscore.model.CreditReport
import javax.inject.Inject

class CreditScoreFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private var _binding: FragmentCreditScoreBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditScoreBinding.inflate(inflater, container, false)
        val view = binding.root
        initViewModel()
        initView()
        return view
    }

    private fun initViewModel() {
        viewModel.getCreditReportObserver().observe(viewLifecycleOwner, Observer<CreditReport> {
            showScoreUI(true)
            binding.score.text = it.creditReportInfo.score.toString()
            binding.maxScoreValue.text = "out of ${it.creditReportInfo.maxScoreValue}"
            initDonutView(
                it.creditReportInfo.score.toFloat(),
                it.creditReportInfo.maxScoreValue.toFloat()
            )
        })
        viewModel.getServerErrorLiveDataObserver().observe(viewLifecycleOwner, Observer<Boolean> {
            if (it) {
                showScoreUI(false)
                showToastMessage()
            }
        })
        viewModel.getCreditReport()
    }

    private fun initView() {
        binding.btnRefresh.setOnClickListener {
            viewModel.getCreditReport()
        }
    }

    private fun initDonutView(score: Float, maxScoreValue: Float) {
        val section1 = DonutSection(
            name = "section_1",
            color = Color.parseColor("#FB1D32"),
            amount = score
        )
        binding.donutView.cap = maxScoreValue
        binding.donutView.submitData(listOf(section1))
    }

    private fun showScoreUI(show: Boolean) {
        binding.yourCreditScore.isVisible = show
        binding.maxScoreValue.isVisible = show
        binding.donutView.isVisible = show
        binding.score.isVisible = show
    }

    private fun showToastMessage() {
        Toast.makeText(context, "Error connecting to server", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreditScoreFragment().apply {}
    }
}