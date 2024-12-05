package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application {
	private File studentFile = null;
	private VBox mainVBox;
	private File majorFile = null;
	FileHandling file = new FileHandling();
	StudentList list1 = new StudentList();
	MajorList list2 = new MajorList();
	private FileChooser fileChooser = new FileChooser();
	private Label studentFileLabel = new Label("Selected Student File: None");
	private Label majorFileLabel = new Label("Selected Major File: None");
	Button selectFileButton = new Button("Select Student File");
	Button selectMajorButton = new Button("Select Major File first");
	Button showBothTablesButton = new Button("Show Both Tables");

	private Label b1 = new Label("major"), b3 = new Label("acceptance grade"), b4 = new Label("tawjihi wieght"),
			b5 = new Label("placement test wieght");
	private Button b6 = new Button("next");
	private Button b7 = new Button("prev");

	private Label l1 = new Label("Major Label 1"), l3 = new Label("Major Label 3"), l4 = new Label("Major Label 4"),
			l5 = new Label("Major Label 5");

	private GridPane p = new GridPane();

	private Label s1 = new Label("student id"), s2 = new Label("student name"), s3 = new Label("tawjihi grade"),
			s4 = new Label("placement test grade"), s5 = new Label("major"), s6 = new Label("addmission mark");

	private Label r1 = new Label("student id"), r2 = new Label("student name"), r3 = new Label("tawjihi grade"),
			r4 = new Label("placement test grade"), r5 = new Label("major"), r6 = new Label("addmission mark");
	GridPane p2 = new GridPane();

	int j = 0;
	private Button b8 = new Button("next");
	private Button b9 = new Button("prev");

	int i = 0;
	private Button dec = new Button("show  your options:");
	Button sug = new Button("majors that are allowed to a student ");

	Button add = new Button("add");
	Button delete = new Button("delete");
	Button update = new Button("update");
	Button search = new Button("search");
	Button stats = new Button("stats");
	Button show1=new Button("dispaly menus after edit");
	HBox hbox = new HBox(sug, add, delete, update, search, stats,show1);

	@Override
	public void start(Stage primaryStage) {

		

		selectMajorButton.setOnAction(e -> {
			fileChooser=new FileChooser();
			fileChooser.setTitle("Choose a File");

			try {
				majorFile = null;
				majorFileLabel.setText("Selected Major File: None");

				list2.getMajorlist().clear();

				majorFile = fileChooser.showOpenDialog(primaryStage);

				if (majorFile != null) {
					majorFileLabel.setText("Selected Major File: " + majorFile.getAbsolutePath());
					file.file2Handle(majorFile);
					list2.setMajorlist(file.makeMajors());

				

				}
			} catch (FileNotFoundException ex) {
				showError("Major file was not found.");
				majorFile = null;
				majorFileLabel.setText("Selected Major File: None");

			} catch (IllegalArgumentException ex) {
				showError("Invalid input in major file.");
				majorFile = null;
				majorFileLabel.setText("Selected Major File: None");

			} catch (ArrayIndexOutOfBoundsException i) {
				showError("Invalid input in major file. (missing info");

			}catch(NullPointerException o) {
				showError("sth went wrong wile reading the file");
				return;
				
			}

		});

		selectFileButton.setOnAction(e -> {
			fileChooser=new FileChooser();

			try {

				studentFile = null;
				studentFileLabel.setText("Selected Major File: None");

				list1.getStudents().clear();
				

				studentFile = fileChooser.showOpenDialog(primaryStage);
				if (studentFile != null) {

					studentFileLabel.setText("Selected Student File: " + studentFile.getAbsolutePath());

					file.file1Handle(studentFile);
					list1.setStudents(file.makeStudents(list2.getMajorlist()));

					
				}
			} catch (FileNotFoundException ex) {
				showError("Student file was not found.");
				studentFile = null;
				studentFileLabel.setText("Selected Student File: None");
			}catch(NullPointerException o) {
				showError("sth went wrong wile reading the file");
				return;
				
			}

		});

		showBothTablesButton.setOnAction(e -> displayBothTables());

		mainVBox = new VBox(10, selectMajorButton, majorFileLabel, selectFileButton, studentFileLabel,
				showBothTablesButton);
		ScrollPane scrollPane = new ScrollPane(mainVBox);
		Scene mainScene = new Scene(scrollPane, 800, 500);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		primaryStage.setTitle("FileChooser and Table Example");
		primaryStage.setScene(mainScene);

		primaryStage.show();

	}

	private void displayBothTables() {
		file.f.clear();
		file.f2.clear();
		

		if (studentFile == null || list1.getStudents() == null||list1.getStudents().size()==0) {
			showError("No student file was selected or student data is missing.");
			return;
		}

		if (majorFile == null || list2.getMajorlist() == null||list2.getMajorlist().size()==0) {
			showError("No major file was selected or major data is missing.");
			return;
		}

		setupMajorsTable();
		
		p.add(b1, 0, 0);

		p.add(b3, 1, 0);
		p.add(b4, 2, 0);
		p.add(b5, 3, 0);
		p.add(l1, 0, 1);
		// p.add(l2, 1, 1);
		p.add(l3, 1, 1);
		p.add(l4, 2, 1);
		p.add(l5, 3, 1);

		setupStudentsTable();
		p2.setHgap(10);
		p2.setVgap(10);

		p2.add(s1, 0, 0);
		p2.add(s2, 1, 0);
		p2.add(s3, 2, 0);
		p2.add(s4, 3, 0);
		p2.add(s5, 4, 0);
		p2.add(s6, 5, 0);

		p2.add(r1, 0, 1);
		p2.add(r2, 1, 1);
		p2.add(r3, 2, 1);
		p2.add(r4, 3, 1);
		p2.add(r5, 4, 1);
		p2.add(r6, 5, 1);

		mainVBox.getChildren().addAll(p, b7, b6, p2, b9, b8);
		if (file.f.size() > 0 || file.f2.size() > 0) {
			showError(" any students or major with missing info has been deleted like :\n " + file.f + "\n" + file.f2);
		}

		

		mainVBox.getChildren().addAll(dec);

dec.setOnAction(e->dec());
		
	}

	HBox T=new HBox();
	VBox t2=new VBox();
	VBox t3=new VBox();	
	
	
void dec() {
	mainVBox.getChildren().removeAll(selectMajorButton, majorFileLabel, selectFileButton, studentFileLabel,showBothTablesButton,p,p2,b6,b7,b8,b9);
	if(!mainVBox.getChildren().contains(hbox)) {
		mainVBox.getChildren().add(hbox);
	}
	
	
	sug.setOnAction(e -> sug());

	update.setOnAction(e -> update());
	stats.setOnAction(e -> stat());
	add.setOnAction(e -> add());
	delete.setOnAction(e->delete());
	search.setOnAction(e->search());
	
	
	
	show1.setOnAction(e -> {

    	T.getChildren().clear();
	    t2.getChildren().clear();
	    t3.getChildren().clear();

        sergp.getChildren().clear();
        sermgp.getChildren().clear();
        
        
        
        ser.getChildren().clear();
        s.getChildren().clear();
        sl4.getChildren().clear();
        topstd.getChildren().clear();
        b.getChildren().clear();
        v.getChildren().clear();
        p4.getChildren().clear();
        p1.getChildren().clear();
        p3.getChildren().clear();
        m2.getChildren().clear();
        m1.getChildren().clear();
        m3.getChildren().clear();
        mupdate.getChildren().clear();
        supdate.getChildren().clear();
        h.getChildren().clear();
        psug.getChildren().clear();
        alter.getChildren().clear();
        alt.getChildren().clear();
        ad.getChildren().clear();
        
     
        del.getChildren().clear();
        dgp.getChildren().clear();
        dmgp.getChildren().clear();
     
        

	    try {
	        if (list1 == null || list1.getStudents() == null || list1.getStudents().size() == 0) {
	            Label noStudents = new Label("No students available.");
	            t2.getChildren().add(noStudents);
	        } else {
	            for (int i = 0; i < list1.getStudents().size(); i++) {
	                Label l = new Label(list1.getStudents().getAt(i).toString());
	                t2.getChildren().add(l);
	            }
	        }

	        if (list2 == null || list2.getMajorlist() == null || list2.getMajorlist().size() == 0) {
	            Label noMajors = new Label("No majors available.");
	            t3.getChildren().add(noMajors);
	        } else {
	            for (int j = 0; j < list2.getMajorlist().size(); j++) {
	                Label n = new Label(list2.getMajorlist().getAt(j).toString());
	                t3.getChildren().add(n);
	            }
	        }

	    } catch (NullPointerException ex) {
	        Label errorLabel = new Label("Error: Some data is missing.");
	        T.getChildren().add(errorLabel);
	    }

	    T.getChildren().addAll(t2, t3);
	    if(!mainVBox.getChildren().contains(T)) {
	    	mainVBox.getChildren().add(T);
	    }
	});


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	GridPane psug = new GridPane();
	VBox v = new VBox();
	VBox h = new VBox();
	HBox alter = new HBox();
	VBox alt = new VBox();

	Label label1 = new Label("Insert the ID of the student:");
	TextField f = new TextField();
	Button bb = new Button("Show");
	Label label2 = new Label();
	Label label = new Label("Enter the major you want:");
	TextField t = new TextField();
	Button m = new Button("Show alternative majors");

	private void sug() {

		
		T.getChildren().clear();
	    t2.getChildren().clear();
	    t3.getChildren().clear();

		v.getChildren().clear();

		p4.getChildren().clear();
		p1.getChildren().clear();
		p3.getChildren().clear();
		m2.getChildren().clear();
		m1.getChildren().clear();
		m3.getChildren().clear();
		mupdate.getChildren().clear();
		supdate.getChildren().clear();

		h.getChildren().clear();
		psug.getChildren().clear();
		alter.getChildren().clear();
		alt.getChildren().clear();
		s.getChildren().clear();
		sl4.getChildren().clear();
		topstd.getChildren().clear();
		b.getChildren().clear();

		ad.getChildren().clear();

		sgp.getChildren().clear();
		mgp.getChildren().clear();
		
		dgp.getChildren().clear();
		dmgp.getChildren().clear();

		del.getChildren().clear();
		 
        
        sergp.getChildren().clear();
        sermgp.getChildren().clear();
        
        
        
        ser.getChildren().clear();


		psug.add(label1, 0, 0);
		psug.add(f, 0, 1);
		psug.add(bb, 2, 0);
		psug.add(label2, 1, 1);
		if (!mainVBox.getChildren().contains(psug)) {
			mainVBox.getChildren().addAll(psug);
		}
		Button bb2 = new Button("Press to choose a major and see the rest of this student's choices");

		bb.setOnAction(e -> {
			try {
				h.getChildren().clear();

				if (f.getText().isEmpty()) {
					label2.setText("Invalid input");
					return;
				}

				LinkedList<MajorData> results = list2.majorSuggestion(f.getText().trim(), list1);
				if (results == null) {
					label2.setText("This student's grades are too low or they are not registered yet.");
					h.getChildren().add(label2);
					if (!mainVBox.getChildren().contains(h))
						mainVBox.getChildren().add(h);
					return;
				}

				for (int i = 0; i < results.size(); i++) {
					Label l = new Label(results.getAt(i).toString());
					if (!h.getChildren().contains(l)) {
						h.getChildren().add(l);
					}
				}
			} catch (IllegalArgumentException ei) {
				showError("invaid input");

			}

			if (!h.getChildren().contains(bb2)) {
				h.getChildren().add(bb2);
			}
			if (!mainVBox.getChildren().contains(h)) {
				mainVBox.getChildren().addAll(h);
			}
		});

		bb2.setOnAction(e -> {
			try {
				alter.getChildren().clear();
				alter.getChildren().addAll(label, t, m);

				if (!mainVBox.getChildren().contains(alter)) {
					mainVBox.getChildren().addAll(alter);
				}

			} catch (IllegalArgumentException ei) {
				showError("invaid input");

			}
		});

		Label label3 = new Label();

		m.setOnAction(e -> {
			try {
				alt.getChildren().clear();

				if (t.getText().isEmpty()) {
					label3.setText("Invalid input");
					alt.getChildren().add(label3);
					if (!mainVBox.getChildren().contains(alt))
						mainVBox.getChildren().add(alt);
					return;
				}

				MajorData major = list2.majorValidation(t.getText().trim());
				if (major == null) {
					label3.setText("Invalid major input");
					alt.getChildren().add(label3);
					if (!mainVBox.getChildren().contains(alt))
						mainVBox.getChildren().add(alt);
					return;
				}

				LinkedList<MajorData> n = list2.majorAlt(f.getText().trim(), major, list1);
				if (n == null) {
					label3.setText("No majors left");
					alt.getChildren().add(label3);
					if (!mainVBox.getChildren().contains(alt))
						mainVBox.getChildren().add(alt);
					return;
				}

				for (int i = 0; i < n.size(); i++) {
					Label majorLabel = new Label(n.getAt(i).toString());
					if (!alt.getChildren().contains(majorLabel)) {
						alt.getChildren().add(majorLabel);
					}
				}

				if (!mainVBox.getChildren().contains(alt))
					mainVBox.getChildren().add(alt);
			} catch (IllegalArgumentException ei) {
				showError("invaid input");

			}
		});
	}

	Button updateStudent = new Button("update student");
	Button updateMajor = new Button("update major");

	Button sname = new Button("update student name");
	Button stg = new Button("update student tawjihi grade");
	Button spt = new Button("update student placment test grade");

	Button mname = new Button("update major name");
	Button mtg = new Button("update major  tawjihi wieght");
	Button mpt = new Button("update major placment test wieght");

	// 2
	HBox supdate = new HBox();
	HBox mupdate = new HBox();

	// 1
	GridPane p1 = new GridPane();
	GridPane p4 = new GridPane();
	GridPane p3 = new GridPane();

	// 1
	GridPane m1 = new GridPane();
	GridPane m2 = new GridPane();
	GridPane m3 = new GridPane();

	public void update() {
		 
        
        sergp.getChildren().clear();
        sermgp.getChildren().clear();
        T.getChildren().clear();
	    t2.getChildren().clear();
	    t3.getChildren().clear();

        
        
        ser.getChildren().clear();
		v.getChildren().clear();
		p4.getChildren().clear();
		p1.getChildren().clear();
		p3.getChildren().clear();
		m2.getChildren().clear();
		m1.getChildren().clear();
		m3.getChildren().clear();
		mupdate.getChildren().clear();
		supdate.getChildren().clear();
		h.getChildren().clear();
		psug.getChildren().clear();
		alter.getChildren().clear();
		alt.getChildren().clear();

		s.getChildren().clear();
		sl4.getChildren().clear();
		topstd.getChildren().clear();
		b.getChildren().clear();

		s.getChildren().clear();
		sl4.getChildren().clear();
		topstd.getChildren().clear();
		b.getChildren().clear();

		ad.getChildren().clear();

		sgp.getChildren().clear();
		mgp.getChildren().clear();
		dgp.getChildren().clear();
		dmgp.getChildren().clear();

		del.getChildren().clear();


		v.getChildren().addAll(updateStudent, updateMajor);
		if (!mainVBox.getChildren().contains(v)) {
			mainVBox.getChildren().add(v);
		}

		updateStudent.setOnAction(e -> {
			supdate.getChildren().clear();
			mupdate.getChildren().clear();

			if (!supdate.getChildren().contains(sname)) {
				supdate.getChildren().addAll(sname, stg, spt);
			}
			if (!mainVBox.getChildren().contains(supdate)) {
				mainVBox.getChildren().add(supdate);
			}
		});

		Button ok1 = new Button("ok");

		Button sid = new Button("enter student id");
		TextField sida = new TextField();
		Button snewname = new Button("enter the new name");
		TextField snewnamea = new TextField();

		sname.setOnAction(e -> {
			p1.getChildren().clear();
			p4.getChildren().clear();
			p3.getChildren().clear();
			m2.getChildren().clear();
			m1.getChildren().clear();
			m3.getChildren().clear();

			p1.add(sid, 0, 0);
			p1.add(sida, 0, 1);
			p1.add(snewname, 1, 0);
			p1.add(snewnamea, 1, 1);
			p1.add(ok1, 2, 0);
			if (!mainVBox.getChildren().contains(p1)) {
				mainVBox.getChildren().addAll(p1);
			}
		});

		ok1.setOnAction(e -> {
			if (sida.getText().isEmpty() || snewnamea.getText().isEmpty()) {
				showError("missing input");
				return;
			}
			if (list1.updateName(sida.getText().trim(), snewnamea.getText().trim(), studentFile) == true) {
				showError("student name has been updated successfully");

			} else {

				showError("couldn't find the student");
			}

		});
		Button sid1 = new Button("enter student id");
		TextField sida1 = new TextField();
		Button snewtg = new Button("enter the new grade");
		TextField snewtga = new TextField();

		Button ok2 = new Button("ok");

		stg.setOnAction(e -> {
			p4.getChildren().clear();
			p1.getChildren().clear();
			p3.getChildren().clear();
			m2.getChildren().clear();
			m1.getChildren().clear();
			m3.getChildren().clear();

			p4.add(sid1, 0, 0);
			p4.add(sida1, 0, 1);
			p4.add(snewtg, 1, 0);
			p4.add(snewtga, 1, 1);
			p4.add(ok2, 2, 0);
			if (!mainVBox.getChildren().contains(p4)) {
				mainVBox.getChildren().addAll(p4);
			}
		});

		ok2.setOnAction(e -> {
			if (sida1.getText().isEmpty() || snewtga.getText().isEmpty()) {
				showError("missing input");
				return;

			}
			try {
				if (list1.updateTg(sida1.getText().trim(), Double.parseDouble(snewtga.getText().trim()),
						studentFile) == true) {
					showError("student tawjihi grade has been updated successfully");

				} else {

					showError("couldn't find the student");
				}
			} catch (IllegalArgumentException ep) {
				showError("invalid input");
			}

		});

		Button sid2 = new Button("enter student id");
		TextField sida2 = new TextField();
		Button snewpt = new Button("enter the new grade");
		TextField snewpta = new TextField();

		Button ok3 = new Button("ok");

		spt.setOnAction(e -> {
			p4.getChildren().clear();
			p1.getChildren().clear();
			p3.getChildren().clear();
			m2.getChildren().clear();
			m1.getChildren().clear();
			m3.getChildren().clear();

			p3.add(sid2, 0, 0);
			p3.add(sida2, 0, 1);
			p3.add(snewpt, 1, 0);
			p3.add(snewpta, 1, 1);
			p3.add(ok3, 2, 0);
			if (!mainVBox.getChildren().contains(p3)) {
				mainVBox.getChildren().addAll(p3);
			}
		});

		ok3.setOnAction(e -> {
			if (sida2.getText().isEmpty() || snewpta.getText().isEmpty()) {
				showError("missing input");
				return;

			}
			try {
				if (list1.updatePt(sida2.getText().trim(), Integer.parseInt(snewpta.getText().trim()),
						studentFile) == true) {
					showError("placment test grade has been updated successfully");

				} else {

					showError("couldn't find the student");
				}
			} catch (IllegalArgumentException ep) {
				showError("invalid input");
			}

		});

		updateMajor.setOnAction(e -> {
			mupdate.getChildren().clear();
			supdate.getChildren().clear();
			if (!mupdate.getChildren().contains(mname)) {
				mupdate.getChildren().addAll(mname, mtg, mpt);
			}
			if (!mainVBox.getChildren().contains(mupdate)) {
				mainVBox.getChildren().add(mupdate);
			}
		});

		Button go1 = new Button("go");

		Button mid = new Button("enter major name");
		TextField mida = new TextField();
		Button mnewname = new Button("enter the new name");
		TextField mnewnamea = new TextField();

		mname.setOnAction(e -> {
			m1.getChildren().clear();
			m2.getChildren().clear();
			m3.getChildren().clear();

			p1.getChildren().clear();
			p4.getChildren().clear();
			p3.getChildren().clear();

			m1.add(mid, 0, 0);
			m1.add(mida, 0, 1);
			m1.add(mnewname, 1, 0);
			m1.add(mnewnamea, 1, 1);
			m1.add(go1, 2, 0);

			mainVBox.getChildren().addAll(m1);

		});

		go1.setOnAction(e -> {
			if (mida.getText().isEmpty() || mnewnamea.getText().isEmpty()) {
				showError("missing input");
				return;
			}
			if (list2.updateNameM(mida.getText().trim(), mnewnamea.getText().trim(), majorFile) == true) {
				showError("major name has been updated successfully");

			} else {

				showError("couldn't find the majort");
			}

		});

		Button mid1 = new Button("enter major name");
		TextField mida1 = new TextField();
		Button mnewtg = new Button("enter the new wieght");
		TextField mnewtga = new TextField();

		Button go2 = new Button("go");

		mtg.setOnAction(e -> {
			m1.getChildren().clear();
			m2.getChildren().clear();
			m3.getChildren().clear();
			p1.getChildren().clear();
			p4.getChildren().clear();
			p3.getChildren().clear();

			m2.add(mid1, 0, 0);
			m2.add(mida1, 0, 1);
			m2.add(mnewtg, 1, 0);
			m2.add(mnewtga, 1, 1);
			m2.add(go2, 2, 0);
			if (!mainVBox.getChildren().contains(m2)) {
				mainVBox.getChildren().addAll(m2);
			}
		});

		go2.setOnAction(e -> {
			if (mida1.getText().isEmpty() || mnewtga.getText().isEmpty()) {
				showError("missing input");
				return;

			}
			try {
				if (list2.updateTw(mida1.getText().trim(), Double.parseDouble(mnewtga.getText().trim()),
						majorFile) == true) {
					showError("major tawjihi grade has been updated successfully");

				} else {

					showError("couldn't find the major");
				}
			} catch (IllegalArgumentException ep) {
				showError("invalid input");
			}

		});

		Button mid2 = new Button("enter student id");
		TextField mida2 = new TextField();
		Button mnewpt = new Button("enter the new grade");
		TextField mnewpta = new TextField();

		Button go3 = new Button("go");

		mpt.setOnAction(e -> {
			m2.getChildren().clear();
			m1.getChildren().clear();
			m3.getChildren().clear();
			p1.getChildren().clear();
			p4.getChildren().clear();
			p3.getChildren().clear();

			m3.add(mid2, 0, 0);
			m3.add(mida2, 0, 1);
			m3.add(mnewpt, 1, 0);
			m3.add(mnewpta, 1, 1);
			m3.add(go3, 2, 0);
			if (!mainVBox.getChildren().contains(m3)) {
				mainVBox.getChildren().addAll(m3);
			}
		});

		go3.setOnAction(e -> {
			if (mida2.getText().isEmpty() || mnewpta.getText().isEmpty()) {
				showError("missing input");
				return;

			}
			try {
				if (list2.updateTp(mida2.getText().trim(), Integer.parseInt(mnewpta.getText().trim()),
						majorFile) == true) {
					showError("placment test wieght has been updated successfully");

				} else {

					showError("couldn't find the major");
				}
			} catch (IllegalArgumentException ep) {
				showError("invalid input");
			}

		});

	}

	GridPane s = new GridPane();
	Label stat1 = new Label("acceptance rate for all majors:    "),
			stat2 = new Label("  number of all students in all majors:   "),
			stat3 = new Label("  rejected students in all majors:  ");
	Button stat4 = new Button("n top students in major: ");
	Label result1 = new Label(), result2 = new Label(), result3 = new Label();
	VBox b = new VBox();

	Label statl4 = new Label("enter the number of top students you want");
	Label statl42 = new Label("inter the name of the major");
	TextField statl4a = new TextField();
	TextField statl42a = new TextField();
	Button show = new Button("show");
	VBox topstd = new VBox();
	GridPane sl4 = new GridPane();

	void stat() {
		try {
			T.getChildren().clear();
		    t2.getChildren().clear();
		    t3.getChildren().clear();

 
	        
	        sergp.getChildren().clear();
            sermgp.getChildren().clear();
	        
	        
	        
	        ser.getChildren().clear();
			s.getChildren().clear();
			sl4.getChildren().clear();
			topstd.getChildren().clear();
			b.getChildren().clear();

			v.getChildren().clear();
			p4.getChildren().clear();
			p1.getChildren().clear();
			p3.getChildren().clear();
			m2.getChildren().clear();
			m1.getChildren().clear();
			m3.getChildren().clear();
			mupdate.getChildren().clear();
			supdate.getChildren().clear();
			h.getChildren().clear();
			psug.getChildren().clear();
			alter.getChildren().clear();
			alt.getChildren().clear();

			ad.getChildren().clear();

			sgp.getChildren().clear();
			mgp.getChildren().clear();
			dgp.getChildren().clear();
			dmgp.getChildren().clear();

			del.getChildren().clear();


			String x = Integer.toString(list2.studentsInAllMajors());
			String y = Integer.toString(list2.rejectedInAllMajors(list1));
			LinkedList<String> n = list2.acceptanceRateAllMajors(list1);
			if (n.size() <= 0 || n == null) {
				showError("no students or no majors yet");
				return;
			}

			for (int i = 0; i < n.size(); i++) {
				Label l = new Label(n.getAt(i));

				b.getChildren().add(l);

			}

			result2.setText(x);

			result3.setText(y);

			s.add(stat1, 0, 0);
			s.add(b, 0, 1);
			s.add(stat2, 3, 0);
			s.add(result2, 5, 0);
			s.add(result3, 9, 0);
			s.add(stat3, 7, 0);
			s.add(stat4, 10, 0);
			s.setVgap(20);

			if (!mainVBox.getChildren().contains(s)) {
				mainVBox.getChildren().addAll(s);
			}

			stat4.setOnAction(e -> {
				s.getChildren().clear();
				sl4.getChildren().clear();
				topstd.getChildren().clear();
				b.getChildren().clear();

				sl4.add(statl4, 0, 0);
				sl4.add(statl42, 0, 1);
				sl4.add(statl4a, 1, 0);
				sl4.add(statl42a, 1, 1);
				sl4.add(show, 2, 0);

				if (!mainVBox.getChildren().contains(sl4)) {
					mainVBox.getChildren().add(sl4);
				}
			});

			show.setOnAction(e -> {
				s.getChildren().clear();

				topstd.getChildren().clear();
				b.getChildren().clear();
				try {
					if (statl4a.getText().isEmpty() || statl42a.getText().isEmpty()) {
						showError("missing info");
						return;

					}

					int q = Integer.parseInt(statl4a.getText().trim());
					if (q == 0) {
						showError("invalid input");
						return;
					}

					MajorData m = file.findingMajor(list2.getMajorlist(), statl42a.getText().trim());
					if (m == null) {
						showError("this major doesn't exist");
						return;

					}
					LinkedList<String> std = m.topMajor(q);
					if (std.size() <= 0) {
						showError("this major has no student");
						return;
					}
					topstd.getChildren().clear();
					for (int i = 0; i < std.size(); i++) {
						Label k = new Label(std.getAt(i));
						topstd.getChildren().add(k);
					}

					if (!mainVBox.getChildren().contains(topstd)) {
						mainVBox.getChildren().add(topstd);
					}
				} catch (IllegalArgumentException m) {
					showError("invalid input");
				} catch (NullPointerException r) {
					showError("this major doesn't have this many students");
				}

			});

		} catch (IllegalArgumentException m) {
			showError("invalid input");
		} catch (NullPointerException r) {
			showError("invalid input");
		}

	}

	VBox ad = new VBox();
	Button adm = new Button("      add a major  ");
	Button ads = new Button("      add a student ");

	GridPane sgp = new GridPane();
	Label name = new Label("enter your new name ");
	Label tw = new Label("enter your tawjihi grade ");
	Label pt = new Label("enter your placment test ");
	Label ma = new Label("enter your chosen major ");

	TextField namea = new TextField();
	TextField twa = new TextField();
	TextField pta = new TextField();
	TextField maa = new TextField();
	Button addStudent = new Button("add student");

	GridPane mgp = new GridPane();
	Label majorname = new Label("enter the major name ");
	Label acceptance = new Label("enter the acceptance grade ");
	Label tww = new Label("enter the tawjihi wieght ");
	Label ptw = new Label("enter placment test weight ");

	TextField majornamea = new TextField();
	TextField acceptacea = new TextField();
	TextField twwa = new TextField();
	TextField ptwa = new TextField();
	Button addMajor = new Button("add Major");

	void add() {
		T.getChildren().clear();
	    t2.getChildren().clear();
	    t3.getChildren().clear();

        
        sergp.getChildren().clear();
        sermgp.getChildren().clear();
        
        
        
        ser.getChildren().clear();

		s.getChildren().clear();
		sl4.getChildren().clear();
		topstd.getChildren().clear();
		b.getChildren().clear();

		v.getChildren().clear();
		p4.getChildren().clear();
		p1.getChildren().clear();
		p3.getChildren().clear();
		m2.getChildren().clear();
		m1.getChildren().clear();
		m3.getChildren().clear();
		mupdate.getChildren().clear();
		supdate.getChildren().clear();
		h.getChildren().clear();
		psug.getChildren().clear();
		alter.getChildren().clear();
		alt.getChildren().clear();

		ad.getChildren().clear();
		
		dgp.getChildren().clear();
		dmgp.getChildren().clear();

		del.getChildren().clear();

		ad.getChildren().addAll(adm, ads);
		ad.setMinWidth(10);

		if (!mainVBox.getChildren().contains(ad)) {
			mainVBox.getChildren().add(ad);

			ads.setOnAction(e -> {

				mgp.getChildren().clear();
				sgp.getChildren().clear();

				sgp.add(name, 0, 0);
				sgp.add(tw, 1, 0);
				sgp.add(pt, 2, 0);
				sgp.add(ma, 3, 0);

				sgp.add(namea, 0, 1);
				sgp.add(twa, 1, 1);
				sgp.add(pta, 2, 1);
				sgp.add(maa, 3, 1);
				sgp.add(addStudent, 4, 0);

				if (!mainVBox.getChildren().contains(sgp)) {
					mainVBox.getChildren().add(sgp);
				}

			});

			addStudent.setOnAction(e -> {
				try {

					if (namea.getText().isEmpty() || twa.getText().isEmpty() || pta.getText().isEmpty()
							|| maa.getText().isEmpty()) {
						showError("Missing information. Please fill out all fields.");
						return;
					}

					MajorData major = file.findingMajor(list2.getMajorlist(), maa.getText().trim());
					if (major == null) {
						showError("This major doesn't exist.");
						return;
					}

					String name = namea.getText().trim();
					double tawjihi = Double.parseDouble(twa.getText().trim());

					double placementDouble = Double.parseDouble(pta.getText().trim());
					int placement = (int) placementDouble;

					if (tawjihi > 100.00 || placementDouble > 100.00) {
						showError("invalid grades");
						return;
					}

					Student std = new Student();
					std.setName(name);
					std.setTjGrade(tawjihi);
					std.setPt(placement);
					std.setcMajor(major);

					if (list1.add(std, studentFile)) {
						showError(std.toString() + " has been added successfully.");
					} else {
						showError("This student already exists.");
					}

				} catch (NumberFormatException ex) {
					showError("Invalid input for Tawjihi or placement test score. Please enter numeric values.");
				} catch (IllegalArgumentException ex) {
					showError("Invalid input: " + ex.getMessage());
				} catch (NullPointerException m) {
					showError("sth went wrong");
					return;
				}
			});

			adm.setOnAction(e -> {
				sgp.getChildren().clear();
				mgp.getChildren().clear();

				mgp.add(majorname, 0, 0);
				mgp.add(acceptance, 1, 0);
				mgp.add(tww, 2, 0);
				mgp.add(ptw, 3, 0);

				mgp.add(majornamea, 0, 1);
				mgp.add(acceptacea, 1, 1);
				mgp.add(twwa, 2, 1);
				mgp.add(ptwa, 3, 1);
				mgp.add(addMajor, 4, 0);

				if (!mainVBox.getChildren().contains(mgp)) {
					mainVBox.getChildren().add(mgp);
				}

			});

			addMajor.setOnAction(e -> {
				try {

					if (majornamea.getText().isEmpty() || acceptacea.getText().isEmpty() || twwa.getText().isEmpty()
							|| ptwa.getText().isEmpty()) {
						showError("missing info");
						return;
					}

					

					String name = majornamea.getText().trim();

					double a = Double.parseDouble(acceptacea.getText().trim());
					double tawjihi = Double.parseDouble(twwa.getText().trim());
					double pt = Double.parseDouble(ptwa.getText().trim());
					int a1 = (int) a;
					MajorData m = new MajorData(name, a1, tawjihi, pt);

					if (tawjihi + pt != 1) {
						showError("the weightings for placment test and tawjihi must equal 1");
						return;
					}

					if (a > 100.00) {
						showError("invalid acceptance grade");
						return;
					}
					if (list2.add(m, majorFile) == true) {
						showError(m.toString() + " has been added");

					} else {
						showError("this major already exist");
					}
				} catch (IllegalArgumentException o) {
					showError("invaild input");
					return;
				} catch (NullPointerException m) {
					showError("sth went wrong");
					return;
				}

			});

		}

	}

	VBox del = new VBox();
	Button delm = new Button("      delete a major  ");
	Button dels = new Button("      delete a student ");

	GridPane dgp = new GridPane();
	Label id = new Label("enter student id ");

	TextField ida = new TextField();

	Button deletStudent = new Button("delets student");

	GridPane dmgp = new GridPane();
	Label majorn = new Label("enter the major name ");

	TextField majorna = new TextField();

	Button deleteMajor = new Button("delete Major");

	void delete() {
	    try {
 
	    	T.getChildren().clear();
		    t2.getChildren().clear();
		    t3.getChildren().clear();

	        sergp.getChildren().clear();
            sermgp.getChildren().clear();
	        
	        
	        
	        ser.getChildren().clear();
	        s.getChildren().clear();
	        sl4.getChildren().clear();
	        topstd.getChildren().clear();
	        b.getChildren().clear();
	        v.getChildren().clear();
	        p4.getChildren().clear();
	        p1.getChildren().clear();
	        p3.getChildren().clear();
	        m2.getChildren().clear();
	        m1.getChildren().clear();
	        m3.getChildren().clear();
	        mupdate.getChildren().clear();
	        supdate.getChildren().clear();
	        h.getChildren().clear();
	        psug.getChildren().clear();
	        alter.getChildren().clear();
	        alt.getChildren().clear();
	        ad.getChildren().clear();
	        
	     
	        del.getChildren().clear();
	        del.getChildren().addAll(delm, dels);

	        if (!mainVBox.getChildren().contains(del)) {
	            mainVBox.getChildren().add(del);

	           
	            dels.setOnAction(e -> {
	                dgp.getChildren().clear();
	                dmgp.getChildren().clear();
	                dgp.add(id, 0, 0);
	                dgp.add(ida, 1, 0);
	                dgp.add(deletStudent, 4, 0);

	                if (!mainVBox.getChildren().contains(dgp)) {
	                    mainVBox.getChildren().add(dgp);
	                }
	            });

	            deletStudent.setOnAction(e -> {
	                try {
	                    if (ida.getText().isEmpty()) {
	                        showError("Missing information. Please fill out the student ID.");
	                        return;
	                    }

	                  if( list1.delete(ida.getText().trim(), studentFile)==true) {
	                	  showError("Student with ID " + ida.getText().trim() + " has been deleted.");
	                	  
	                  }
	                   else {
	                        showError("Student not found.");
	                    }
	                } catch (IllegalArgumentException ex) {
	                    showError("Invalid input: " + ex.getMessage());
	                } catch (NullPointerException ex) {
	                    showError("An unexpected error occurred while deleting the student.");
	                }
	            });

	            
	           delm.setOnAction(e -> {
	                dmgp.getChildren().clear();
	                dgp.getChildren().clear();
	                dmgp.add(majorn, 0, 0);
	                dmgp.add(majorna, 1, 0);
	                dmgp.add(deleteMajor, 4, 0);

	                if (!mainVBox.getChildren().contains(dmgp)) {
	                    mainVBox.getChildren().add(dmgp);
	                }
	            });

	            deleteMajor.setOnAction(e -> {
	                try {
	                    if (majorna.getText().isEmpty()) {
	                        showError("Missing major name.");
	                        return;
	                    }

	                   if(list2.delete(majorna.getText().trim(), majorFile)==true) {
	                   
	                        showError("The major " + majorna.getText().trim() + " has been deleted.");
	                    } else {
	                        showError("Major not found.");
	                    }
	                } catch (IllegalArgumentException ex) {
	                    showError("Invalid input: " + ex.getMessage());
	                } catch (NullPointerException ex) {
	                    showError("An unexpected error occurred while deleting the major.");
	                }
	            });
	        }
	    } catch (NullPointerException ex) {
	        showError("An error occurred while setting up the delete : ");
	    }
	}
	
	
	VBox ser = new VBox();
	Button serm = new Button("      search for a major  ");
	Button sers = new Button("      search for a a student ");

	GridPane sergp = new GridPane();
	Label studentId = new Label("enter student id ");

	TextField studentsIda = new TextField();

	Button searchfors = new Button("search");

	GridPane sermgp = new GridPane();
	Label majornam = new Label("enter the major name ");

	TextField majornama = new TextField();

	Button searchform = new Button("search for Major");

	
	
	void search() {
		
		
	    try {
	    	T.getChildren().clear();
		    t2.getChildren().clear();
		    t3.getChildren().clear();

	        
	        s.getChildren().clear();
	        sl4.getChildren().clear();
	        topstd.getChildren().clear();
	        b.getChildren().clear();
	        v.getChildren().clear();
	        p4.getChildren().clear();
	        p1.getChildren().clear();
	        p3.getChildren().clear();
	        m2.getChildren().clear();
	        m1.getChildren().clear();
	        m3.getChildren().clear();
	        mupdate.getChildren().clear();
	        supdate.getChildren().clear();
	        h.getChildren().clear();
	        psug.getChildren().clear();
	        alter.getChildren().clear();
	        alt.getChildren().clear();
	        ad.getChildren().clear();
	        
	     
	        del.getChildren().clear();
	       
	        
	        
	       
	        
	        ser.getChildren().clear();
	        ser.getChildren().addAll(serm,sers);
	       

	        if (!mainVBox.getChildren().contains(ser)) {
	            mainVBox.getChildren().add(ser);

	           
	            sers.setOnAction(e -> {
	                sergp.getChildren().clear();
	                sermgp.getChildren().clear();
	                sergp.add(studentId, 0, 0);
	                sergp.add(studentsIda, 1, 0);
	                sergp.add(searchfors, 4, 0);

	                if (!mainVBox.getChildren().contains(sergp)) {
	                    mainVBox.getChildren().add(sergp);
	                }
	            });

	            searchfors.setOnAction(e -> {
	                try {
	                    if (studentsIda.getText().isEmpty()) {
	                        showError("Missing information. Please fill out the student ID.");
	                        return;
	                    }

	                   String s= list1.search(studentsIda.getText().trim());
	                   if(s==null||s.isEmpty()) {
	                	   showError("student is not found");
	                   }else {
	                	   showError(s);
	                   }
	                   
	                } catch (IllegalArgumentException ex) {
	                    showError("Invalid input: " + ex.getMessage());
	                } catch (NullPointerException ex) {
	                    showError("An unexpected error occurred while deleting the student.");
	                }
	            });

	            
	           serm.setOnAction(e -> {
	        	   sergp.getChildren().clear();
	                sermgp.getChildren().clear();
	                sermgp.add(majornam, 0, 0);
	                sermgp.add( majornama, 1, 0);
	                sermgp.add(searchform , 4, 0);

	                if (!mainVBox.getChildren().contains( sermgp)) {
	                    mainVBox.getChildren().add( sermgp);
	                }
	            });

	           searchform.setOnAction(e -> {
	                try {
	                    if (majornama.getText().isEmpty()) {
	                        showError("Missing major name.");
	                        return;
	                    }
	                    

		                   String s= list2.search(majornama.getText().trim());
		                   if(s==null|s.isEmpty()) {
		                	   showError("major is not found");
		                   }else {
		                	   showError(s);
		                   }
		                   

	                } catch (IllegalArgumentException ex) {
	                    showError("Invalid input: " + ex.getMessage());
	                } catch (NullPointerException ex) {
	                    showError("major is not found.");
	                }
	            });
	        }
	    } catch (NullPointerException ex) {
	        showError("major is not found : ");
	    }
	}
		
		
		
		
		
		
	


	private void setupMajorsTable() {

		if (i < 0 || i >= list2.getMajorlist().size()) {

			l1.setText("This is the last major");

			l3.setText(null);
			l4.setText(null);
			l5.setText(null);
			return;
		}

		l1.setText(list2.getMajorlist().getAt(i).getMajor());

		l3.setText(Integer.toString(list2.getMajorlist().getAt(i).getAcceptanceg()));
		l4.setText(Double.toString(list2.getMajorlist().getAt(i).getTwWieght()));
		l5.setText(Double.toString(list2.getMajorlist().getAt(i).getPtWieght()));

		b6.setOnAction(e -> {
			if (i < list2.getMajorlist().size() - 1) {
				i = i + 1;
				setupMajorsTable();
			} else {
				l1.setText("This is the last major");

				l3.setText(null);
				l4.setText(null);
				l5.setText(null);
			}
		});

		b7.setOnAction(e -> {
			if (i > 0) {
				i = i - 1;
				setupMajorsTable();
			} else {
				l1.setText("This is the first major");

				l3.setText(null);
				l4.setText(null);
				l5.setText(null);
			}
		});
	}

	private void setupStudentsTable() {

		try {
			if (j < 0 || j >= list1.getStudents().size()) {
				r1.setText("out of number");
				r2.setText(null);
				r3.setText(null);
				r4.setText(null);
				r5.setText(null);
				r6.setText(null);
				return;
			}
			r1.setText(list1.getStudents().getAt(j).getStudentId());
			r2.setText(list1.getStudents().getAt(j).getName());
			r3.setText(Double.toString(list1.getStudents().getAt(j).getTjGrade()));
			r4.setText(Integer.toString(list1.getStudents().getAt(j).getPt()));
			r6.setText((list1.getStudents().getAt(j).academicval(list1.getStudents().getAt(j).getcMajor())));
			r5.setText(list1.getStudents().getAt(j).getcMajor().getMajor());

			b8.setOnAction(e -> {
				if (j < list1.getStudents().size() - 1) {

					j = j + 1;
					setupStudentsTable();
				} else {
					r1.setText("last student");
					r2.setText(null);
					r3.setText(null);
					r4.setText(null);
					r5.setText(null);
					r6.setText(null);

				}
			});

			b9.setOnAction(e -> {
				if (j > 0) {
					j = j - 1;
					setupStudentsTable();

				} else {
					r1.setText("this is the first student");
					r2.setText(null);
					r3.setText(null);
					r4.setText(null);
					r5.setText(null);
					r6.setText(null);

				}
			});

		} catch (NumberFormatException y) {
			showError(" missing info about this student");

		} catch (IllegalArgumentException t) {
			showError(" missing info about this student");

		} catch (NullPointerException i) {
			showError(" the major this student wants doesn't exist");

		}

	}

	private void showError(String message) {
		Label errorLabel = new Label(message);
		Scene errorScene = new Scene(errorLabel, 300, 200);
		Stage errorStage = new Stage();
		errorStage.setScene(errorScene);
		errorStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
