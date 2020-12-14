package ui.views.forms.search;

import javafx.scene.layout.GridPane;
import ui.controllers.WineController;
import ui.models.form.SearchWineInput;
import ui.models.utils.TypeSearch;
import ui.views.forms.IForm;

/**
 * The {@code SearchWineByYear} is the class to find wine by year
 * @see Search
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class SearchWineByYear extends Search implements IForm<GridPane, WineController> {
    private GridPane searchByYearGrid;

    public SearchWineByYear(){
       super();
       this.searchByYearGrid=super.getGrid();
    }
    public String getTitle()
    {
        return SearchWineInput.FieldByYear.TITLE;
    }

    @Override
    public GridPane getGrid(WineController controller)
    {
        GridPane.setConstraints(SearchWineInput.FieldByYear.YEAR_LABEL,  0, 0);
        GridPane.setConstraints(SearchWineInput.FieldByYear.YEAR_INPUT, 1, 0);
        super.getButton().setOnAction(e->{
            controller.getAll(TypeSearch.BY_YEAR, SearchWineInput.FieldByYear.YEAR_INPUT.getText() );

        }
        );
        GridPane.setConstraints(super.getButton(), 1, 2); //second coloumn, third row

        this.searchByYearGrid.getChildren().addAll(SearchWineInput.FieldByYear.YEAR_LABEL, SearchWineInput.FieldByYear.YEAR_INPUT,super.getButton());
        return this.searchByYearGrid;
    }
}
