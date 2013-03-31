package com.spyone.util;

import java.text.Format;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.text.TextAlignment;
import javafx.scene.Node;
import javafx.geometry.Pos;

import javafx.util.Callback;

@SuppressWarnings("restriction")
public class FormattedTableCellFactory<S, T> implements
		Callback<TableColumn<S, T>, TableCell<S, T>> {
	private TextAlignment alignment;
	private Format format;
	private boolean editable;

	public TextAlignment getAlignment() {
		return alignment;
	}

	public void setAlignment(TextAlignment alignment) {
		this.alignment = alignment;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	@SuppressWarnings("unchecked")
	public TableCell<S, T> call(TableColumn<S, T> p) {
		TableCell<S, T> cell = new TableCell<S, T>() {
			public void updateItem(Object item, boolean empty) {
				if (item == getItem()) {
					return;
				}
				super.updateItem((T) item, empty);
				if (item == null) {
					super.setText(null);
					super.setGraphic(null);
				} else if (format != null) {
					super.setText(format.format(item));
				} else if (item instanceof Node) {
					super.setText(null);
					super.setGraphic((Node) item);
				} else {
					super.setText(item.toString());
					super.setGraphic(null);
				}
			}
		};		
		cell.setTextAlignment(TextAlignment.CENTER);
		cell.setAlignment(Pos.CENTER);		
		return cell;
	}

}
