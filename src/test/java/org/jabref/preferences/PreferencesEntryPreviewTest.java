package org.jabref.preferences;

import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import org.jabref.gui.maintable.BibEntryTableViewModel;
import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.testfx.api.FxAssert.verifyThat;

public class PreferencesEntryPreviewTest extends ApplicationTest {
    private PreviewPreferences previewPreferences;
    private BibDatabaseContext database;
    private final PreferencesService preferencesService = mock(PreferencesService.class);

    @Override
    public void start(Stage stage) throws Exception {
        previewPreferences = preferencesService.getPreviewPreferences();
        stage.show();
    }

    @Test
    public void testHoverPreviewContent() {
        BibEntry entry = new BibEntry(BibEntry.DEFAULT_TYPE)
                .withField(StandardField.ABSTRACT, "test abstract")
                .withField(StandardField.AUTHOR, "author")
                .withField(StandardField.DATE, "2001");

        BibEntryTableViewModel.showTooltipProperty().set(true);
        String preview = entry.getBibPreviewTooltip(previewPreferences, database);
        assertEquals(preview, "hello");
    }

    @Test
    public void testCheckBoxStates() {
        // Store initial states for later comparison
        boolean initialCheckBox1State = ((CheckBox) lookup("#showAsTabCheckBox").query()).isSelected();
        boolean initialCheckBox2State = ((CheckBox) lookup("#showTooltipEntryTable").query()).isSelected();

        // Verify initial states
        verifyThat(lookup("#showAsTabCheckBox"), CoreMatchers.equalTo(initialCheckBox1State));
        verifyThat(lookup("#showTooltipEntryTable"), CoreMatchers.equalTo(initialCheckBox2State));

        // Click both checkboxes
        clickOn("#showAsTabCheckBox");
        clickOn("#showTooltipEntryTable");

        // Verify states after clicks (opposite of initial states)
        verifyThat(lookup("#showAsTabCheckBox"), CoreMatchers.equalTo(!initialCheckBox1State));
        verifyThat(lookup("#showTooltipEntryTable"), CoreMatchers.equalTo(!initialCheckBox2State));
    }
}
