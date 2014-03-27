"use_strict"

// function that will drop 'delete' all previously created 
// tables. This is useful to have when creating the tables
function dropPreviouslyCreatedTables(table_list)
{
	var num_tables = table_list.length;
	var tables_dropped = 0;

	// we loop through the list of tables and drop them one by one
	for(var i = 0; i < num_tables; i++)
	{
		db.run("DROP TABLE IF EXISTS " + table_list[i], function(err,data){
			// if we get an error we want to throw it so we can see what it is
			if(err) throw err;

			console.log("Dropped table");

			// we then increment the count of the number of tables we
			// have dropped
			tables_dropped++;

			// we then check if they ahve all been dropped. When this is done
			// we want to move onto the creation part of the script
			if(tables_dropped == num_tables)
			{
				console.log("Finished dropping tables");

				// we now call the first function for creating the tables
				createFilesTable();
			}
		});
	}
}

// function that will create the files table
function createFilesTable()
{
	// using javascripts string concatenation we can easily build the 
	// sql query
	var sql_stmt = "CREATE TABLE files (" +
		"files_id INTEGER PRIMARY KEY, " +
		"name TEXT, " +
		"graph TEXT, " +
		"pos TEXT, " +
		"map BLOB )";

	// we then execute the sql statement
	db.run(sql_stmt, function(err) {
		if(err) throw err;

		// if we get here, the creation went ok so we move onto 
		// creating the next table
		console.log("Table 'files' created");		
		createSessionTable();
	})
}


// function that will create the session table
function createSessionTable()
{
	// again we build the sql statment
	var sql_stmt = "CREATE TABLE session (" +
		"session_id INTEGER PRIMARY KEY," +
		"name TEXT, " +
		"files_id INTEGER, " +
		"FOREIGN KEY(files_id) REFERENCES files(files_id))";

	// and then execute it
	db.run(sql_stmt, function(err) {
		if(err) throw err;
		
		console.log("Table 'session' created");
	
		// THIS IS WHERE YOU NEED TO ADD YOUR CODE
		createPlayerTable();
	});
}

// function that will create player table
function createPlayerTable()
{
	var sql_stmt = "CREATE TABLE player (" +
		"player_id INTEGER PRIMARY KEY, " +
		"type TEXT, " +
		"location INTEGER, " +
		"taxi_tickets INTEGER, " +
		"bus_tickets INTEGER, " +
		"underground_tickets INTEGER, " +
		"double_tickets	INTEGER, " +
		"secret_tickets	INTEGER, " +
		"FOREIGN KEY(session_id) REFERENCES session(session_id))";
	
	//executing
	db.run(sql_stmt, function(err) {
		if(err) throw err;
		
		console.log("Table 'session' created");
		createMoveTable();
	});
}

function createMoveTable()
{
	var_stms = "CREATE TABLE move (" +
		"move_id INTEGER PRIMARY KEY , " +
		"start_location INTEGER, " +
		"end_location INTEGER, " +
		"ticket_type TEXT, " +
		"FOREIGN KEY(player_id) REFERENCES plater(player_id))";
	
	//executing
	db.run(sql_stmt, function(err) {
		if(err) throw err;
		
		console.log("Table 'move' created");
		createMoveTable();
	});
}




// Here is where execution of the script will start
//
// here we require the 'sqlite3' module so that we can use it
// to run the SQL queries. If you are getting an error here, please refer
// to the coursework deescription section about using node.js modules
var sql = require("sqlite3");

// here we create a new database (or connect to one already made)
var db = new sql.Database("test.db");

// this is the list of table names that will be used for the dropping function
var table_list = ['files', 'session', 'player', 'move'];

// here we call the first function 
dropPreviouslyCreatedTables(table_list);

