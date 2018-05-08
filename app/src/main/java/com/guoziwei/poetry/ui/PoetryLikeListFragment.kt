package com.guoziwei.poetry.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.guoziwei.poetry.model.Poetry
import com.guoziwei.poetry.ui.adapter.PoetryAdapter
import org.litepal.crud.DataSupport

/**
 * Created by guoziwei on 2018/4/26 0026.
 */
class PoetryLikeListFragment : ListFragment<Poetry>() {


    companion object {
        fun newInstance(): PoetryLikeListFragment {
            val fragment = PoetryLikeListFragment()
            return fragment
        }
    }


    override fun getAdapter(): BaseQuickAdapter<Poetry, out BaseViewHolder> {
        val adapter = PoetryAdapter()
        adapter.setOnItemClickListener { a, view, position ->
            ContentActivity.launch(context, mAdapter.data[position])
        }
        adapter.setEnableLoadMore(true)
        return adapter
    }

    override fun loadData() {
        val list = DataSupport
                .limit(pageCount)
                .offset((mPage - 1) * pageCount)
                .order("id desc")
                .find(Poetry::class.java)
        loadDataSuccess(list)
    }
}