/*サンプルアプリケーション */
/*
 * JavaScript電卓
 
 * 縦・横の数を入力すると、動的に表を作成する
 * 表のセルをクリックすると、合計が表示される
*/
/* サンプル */
function text_copy(btn_num){
	//console.info("_copy");
	document.forms.Calc.COPYTEXTO.value = document.forms.Calc.COPYTEXTFROM.value;
	//window.alert("コピーしました");
}


/* 電卓 */



/* 表合計 */
var tableid = "tbl1";	//共通で使うテーブルのID
var colsize = 0;
var rowsize = 0;

//alert("init");
console.time("init");
console.info("2-1 init start");

//tblclearボタン////////////////////////////////////////
function tblclear(node){

	var doc = node.ownerDocument;

	//console.dir(arg1);
	console.log(rowsize);
	console.log(colsize);

	var inpval = window.prompt("表を初期化します<br>値を入れてください",0);
	if(inpval==null){	//キャンセル時
		//alert("Cancel !");
		return;
	}
	console.log(inpval);

	var tbl = doc.getElementById(tableid);	//table

	for(r = 1; r < rowsize; r++){
		for(c = 1; c <= colsize; c++){
			cid = "c"+c+"r"+r;
			console.debug(cid);
			var repcel = doc.getElementById(cid);	//cell
			console.debug(repcel.innerHTML);
			repcel.innerHTML = inpval;	//セルへセット
		}
	}
	//合計計算
	goukei(doc);
}


//
//クリックされたら、ダイアログで入力
function clickTile(str, doc) {
	console.debug(str);
	var addcel = doc.getElementById(str);	//今の値
	var getval = String(addcel.innerHTML);	//文字列化
	var inpval = window.prompt("値を入れてください",getval);
	if(inpval==null){	//キャンセル時
		//alert("Cancel !");
		return;
	}
	addcel.innerHTML = inpval;	//セルへセット
	//合計
	goukei(doc);
}

/*
 * 合計計算
 */
function goukei(doc){
	//合計
	var total = 0.0;
	var ctotal = new Array(colsize);
	var rtotal = new Array(rowsize);
	for(r = 1; r <= rowsize; r++){
		if(!rtotal[r-1]) rtotal[r-1] = 0.0;	//undefined対策、ややこしい
		for(c = 1; c <= colsize; c++){
			if(!ctotal[c-1]) ctotal[c-1] = 0.0;	//undefined対策、ややこしい
			cid = "c"+c+"r"+r;
			var cel = doc.getElementById(cid);	//cell特定	cel をcellとつづり間違い
			var num = parseFloat(cel.innerHTML);
			if(!isNaN(num)){						//忘れると全NaNバグ
				rtotal[r-1] = rtotal[r-1] + num;
				ctotal[c-1] = ctotal[c-1] + num;
				total = total + num;
			}
			console.debug(cid + ":" + cel.innerHTML);
		}
	}
	//列合計反映
	for(c = 1; c <= colsize; c++){
		var cel = doc.getElementById("gc"+c).innerHTML = ctotal[c-1];
	}
	//行合計反映
	for(r = 1; r <= rowsize; r++){			//rowとcol間違えると行と列サイズが違うときバグ
		doc.getElementById("gr"+r).innerHTML = ctotal[r-1];
	}
	var cel = doc.getElementById("gzz").innerHTML = total;
}


/*
 *テーブルを生成する
 */
function createtab(col, row, node) {
	//console.info(col);
	//console.info(row);

	var cols = parseInt(col);
	if(isNaN(cols)) {console.log("col is NaN!"); return};
	var rows = parseInt(row);
	if(isNaN(rows)) {console.log("row is NaN!"); return};
	colsize = cols;
	rowsize = rows;

	var doc = node.ownerDocument;

	//前のテーブル削除
	var pre = doc.getElementById(tableid);
	if(pre != null){	
		pre.parentNode.removeChild(pre);
	}

	//テーブル
	var tbl1 = doc.createElement("table");
	tbl1.setAttribute("border",4);
	tbl1.setAttribute("id",tableid);
	//tbl1.setAttribute("width",50 * parseInt(col));

	//キャプション
	var cap1 = doc.createElement("集計表");
	var cap1str = doc.createTextNode("Caption:" + col + "×" + row);
	cap1.appendChild(cap1str);
	tbl1.appendChild(cap1);
	
	//ヘッダ行
	var trh = doc.createElement("tr");
	trh.setAttribute("bgcolor", "#22bb77");

	//見出しカラム
	var th0 = doc.createElement("th");
	th0.setAttribute("bgcolor", "#999999");
	trh.appendChild(th0);
	for(h = 1; h <= cols; h++){
		var th = doc.createElement("th");
		th.appendChild(doc.createTextNode("見出し:" + h ));
		trh.appendChild(th);
	}
	var thg = doc.createElement("th");
	thg.setAttribute("bgcolor", "#FFFF66");
	thg.appendChild(doc.createTextNode("行合計"));
	trh.appendChild(thg);
	tbl1.appendChild(trh);

	//セル
	for(r = 1; r <= rows; r++){
		//行
		var tr = node.ownerDocument.createElement("tr");
		//見出し
		var th = doc.createElement("th");
		var thstr = doc.createTextNode("見出し"+r);
		th.appendChild(thstr);
		tr.appendChild(th);
		//各カラム
		for(c = 1; c <= cols; c++){
			cid = "c"+c+"r"+r;
			var td = doc.createElement("td");
			var tddiv = doc.createElement("div");
			tddiv.setAttribute("id", cid);
			tddiv.setAttribute("onClick", "clickTile(this.id, this.ownerDocument)");
//			var tdstr = doc.createTextNode(cid + ":" + col + "=" + row);
			var tdstr = doc.createTextNode("0");
			tddiv.appendChild(tdstr);
			td.appendChild(tddiv);
			tr.appendChild(td);
		}
		var tdg = doc.createElement("td");
		tdg.setAttribute("bgcolor","#FFFF99");
		tdg.setAttribute("id","gr"+r);
		tdg.setAttribute("bgcolor","#FFFF99");
		var thgstr = doc.createTextNode(" ");
		tdg.appendChild(thgstr);
		tr.appendChild(tdg);

		tbl1.appendChild(tr);
	}

	//合計カラム
	var trz = doc.createElement("tr");
	var thz = doc.createElement("th");
	thz.setAttribute("bgcolor", "#FFFF66");
	thz.appendChild(doc.createTextNode("列合計"));
	trz.appendChild(thz);
	for(z = 1; z <= cols; z++){
		var td = doc.createElement("td");
		td.appendChild(doc.createTextNode(" "));
		td.setAttribute("bgcolor", "#FFFF99");
		td.setAttribute("id", "gc"+z);
		trz.appendChild(td);
	}
	var tdz = doc.createElement("td");
	tdz.setAttribute("bgcolor", "#FFFF66");
	tdz.setAttribute("id", "gzz");
	tdz.appendChild(doc.createTextNode("合計"));
	trz.appendChild(tdz);

	tbl1.appendChild(trz);

	doc.body.appendChild(tbl1);
}

//
console.timeEnd("init");
console.info("2-1 init end");
