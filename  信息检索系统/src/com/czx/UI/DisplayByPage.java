package com.czx.UI;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class DisplayByPage {

	private int currentPage; // 当前页
	private int pageSize;// 每页记录数
	private int pageCount;// 总页数
	private int rsCount;// 总记录数
	private Button btnFirst;// 首页按钮
	private Button btnPrevious;// 上一页按钮
	private Button btnNext;// 下一页按钮
	private Button btnLast;// 末页按钮
	private Label pageInfo;// 显示分页信息标签
	private List list;// 需要分页的列表
	private TableViewer tableViewer;// 显示分页的表格

	public DisplayByPage() {
	}

	
	public static void main(String[] args) {
		DisplayByPage displayByPage = new DisplayByPage();
		displayByPage.InitDisplay();
	}
	
	
	/**
	 * 分类显示的构造函数
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @param btnFirst
	 *            首页按钮
	 * @param btnPrevious
	 *            上一页按钮
	 * @param btnNext
	 *            下一页按钮
	 * @param btnLast
	 *            末页按钮
	 * @param pageInfo
	 *            显示分页信息标签
	 * @param list
	 *            需要分页的列表
	 * @param tableViewer
	 * 
	 */
	public DisplayByPage(int currentPage, int pageSize, Button btnFirst,
			Button btnPrevious, Button btnNext, Button btnLast, Label pageInfo,
			List list, TableViewer tableViewer) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.btnFirst = btnFirst;
		this.btnPrevious = btnPrevious;
		this.btnNext = btnNext;
		this.btnLast = btnLast;
		this.pageInfo = pageInfo;
		this.list = list;
		this.tableViewer = tableViewer;
	}

	/**
	 * 选择每页项数的监听响应
	 * 
	 * @param itemCount
	 *            每页显示项数
	 */
	public void ChooseItemDisplay(String itemCount) {

		if (itemCount.equals("全部")) {
			pageSize = list.size();
		} else {
			pageSize = Integer.valueOf(itemCount);
		}
		currentPage = 0;
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 初始化显示首页
	 */
	public void InitDisplay() {
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 首页按钮的监听响应
	 */
	public void BtnFirstListener() {
		currentPage = 0;
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 上一页按钮的监听响应
	 */
	public void BtnPreviousListener() {
		currentPage--;
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 下一页按钮的监听响应
	 */
	public void BtnNextListener() {
		currentPage++;
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 末页按钮的监听响应
	 */
	public void BtnLastListener() {
		currentPage = pageCount - 1;
		List pageList = getPage();
		tableViewer.setInput(pageList);
		refreshButtonState();
	}

	/**
	 * 
	 * @param qi
	 *            分页信息
	 * @param list
	 *            需要分页的列表记录
	 * @return
	 */
	public List getPage() {

		List pageList = new ArrayList();

		// 得到总记录数
		rsCount = list.size();
		// 计算总页数
		if (rsCount % pageSize == 0)
			pageCount = rsCount / pageSize;
		else
			pageCount = (rsCount / pageSize) + 1;
		// 得到当前页的记录
		for (int i = currentPage * pageSize; i < (currentPage * pageSize + pageSize)
				&& (i < rsCount); i++) {
			pageList.add(list.get(i));
		}
		return pageList;
	}

	/**
	 * 刷新翻页按钮的有效/无效状态
	 */
	public void refreshButtonState() {
		if (pageCount == 0) {// 没有记录时
			btnFirst.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
			pageInfo.setText(currentPage + 1 + "/" + pageCount + "页");
		} else {
			// 如果是第一页，则首页、上一页两按钮无效
			boolean b = (currentPage == 0);
			btnFirst.setEnabled(!b);
			btnPrevious.setEnabled(!b);
			// 如果是最后一页，则末页、下一页两按钮无效
			b = (currentPage == pageCount - 1);
			btnNext.setEnabled(!b);
			btnLast.setEnabled(!b);
			pageInfo.setText("共" + rsCount + "项   第" + (currentPage + 1) + "页"
					+ "(共" + pageCount + "页)");
		}
	}

	// ---------------相关属性的Get/Set方法----------------
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Button getBtnFirst() {
		return btnFirst;
	}

	public void setBtnFirst(Button btnFirst) {
		this.btnFirst = btnFirst;
	}

	public Button getBtnPrevious() {
		return btnPrevious;
	}

	public void setBtnPrevious(Button btnPrevious) {
		this.btnPrevious = btnPrevious;
	}

	public Button getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(Button btnNext) {
		this.btnNext = btnNext;
	}

	public Button getBtnLast() {
		return btnLast;
	}

	public void setBtnLast(Button btnLast) {
		this.btnLast = btnLast;
	}

	public Label getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Label pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}
}
