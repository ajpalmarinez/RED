package org.robotframework.ide.eclipse.main.plugin.tableeditor.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.robotframework.ide.eclipse.main.plugin.model.RobotElement;
import org.robotframework.ide.eclipse.main.plugin.tableeditor.EditorCommand;
import org.robotframework.ide.eclipse.main.plugin.tableeditor.FocusedViewerAccessor;
import org.robotframework.ide.eclipse.main.plugin.tableeditor.RobotEditorCommandsStack;
import org.robotframework.red.viewers.Selections;
import org.robotframework.red.viewers.Viewers;

import com.google.common.base.Optional;

public abstract class E4DeleteCellContentHandler {

    @Execute
    public Object deleteCellContent(@Named(Selections.SELECTION) final IStructuredSelection selection,
            final FocusedViewerAccessor viewerAccessor,
            final RobotEditorCommandsStack commandsStack) {
        final RobotElement element = Selections.getSingleElement(selection, RobotElement.class);
        final ViewerCell focusedCell = viewerAccessor.getFocusedCell();
        final ColumnViewer viewer = viewerAccessor.getViewer();
        final int index = Viewers.createOrderIndexToPositionIndex(viewer, focusedCell.getColumnIndex());
        final int noOfColumns = getNoOfColumns(viewer);

        final Optional<? extends EditorCommand> command = provideCommandForAttributeChange(element, index, noOfColumns);
        if (command.isPresent()) {
            commandsStack.execute(command.get());
        }

        return null;
    }

    protected abstract Optional<? extends EditorCommand> provideCommandForAttributeChange(RobotElement element,
            int index, int noOfColumns);

    private int getNoOfColumns(final ColumnViewer viewer) {
        if (viewer instanceof TreeViewer) {
            return ((TreeViewer) viewer).getTree().getColumnCount();
        } else if (viewer instanceof TableViewer) {
            return ((TableViewer) viewer).getTable().getColumnCount();
        }
        throw new IllegalStateException("Unknown viewer type");
    }
}