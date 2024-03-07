package org.jabref.gui.tooltip;

import javafx.scene.control.TableView;

import org.jabref.gui.maintable.BibEntryTableViewModel;
import org.jabref.gui.util.ViewModelTableRowFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(ApplicationExtension.class)
public class ToolTipDataModelTest {

    @Test
    public void testNullCallBackReturnsNoTooltip() {
        ViewModelTableRowFactory<BibEntryTableViewModel> factory = new ViewModelTableRowFactory<>();
        TableView<BibEntryTableViewModel> tableView = new TableView<>();
        factory.withTooltip(null);
        factory.install(tableView);
        assertEquals(null, tableView.getTooltip());
    }

    @Test
    public void testThatToolTipIsAlwaysTurnedOffByDefault() {
        assertFalse(BibEntryTableViewModel.showTooltipProperty().get());
    }
}
