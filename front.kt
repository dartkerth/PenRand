/**
 * Check if a number lies within a range.
 * Check if a number is out of range.
 * Check if a collection contains an object.
 * See http://kotlinlang.org/docs/reference/ranges.html#ranges
 */

import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import tornadofx.*
import java.time.LocalDate
import java.time.Period


class HelloWorldApp : App(HelloWorld::class, Styles::class)

class Styles : Stylesheet() {
    private var colorTexto: String = "#0A0D30"

    init {
        label {
            fontSize = 14.px
            fontWeight = FontWeight.SEMI_BOLD
            textFill = c(colorTexto)
            //backgroundColor += c("#E8EAF6")
        }
        tabPane {
            backgroundColor += c("#FFFFFF")
            //borderColor += box(c("#1A237E"))
        }
        button {
            backgroundColor += c("#FFFFFF")
            borderColor += box(c(colorTexto))
            fontWeight = FontWeight.BOLD
            fontFamily = "inconsolataGo"
            textFill = c(colorTexto)
        }
        tab {
            backgroundColor += c("#FFFFFF")
            //borderColor += box(c("#1A237E"))
        }
        tabHeaderBackground {
            backgroundColor += c("#FFFFFF")
        }
        columnHeaderBackground {
            backgroundColor += c("#FFFFFF")
        }
    }
}

class HelloWorld : View() {
    /*val myLabel: Label by fxid()

    init {
        myLabel.text = "Hello world"
    }
    private val persons = FXCollections.observableArrayList(
            Person(1, "Samantha Stuart", LocalDate.of(1981,12,4)),
            Person(2, "Tom Marks", LocalDate.of(2001,1,23)),
            Person(3, "Stuart Gills", LocalDate.of(1989,5,23)),
            Person(3, "Nicole Williams", LocalDate.of(1998,8,11))
    )

    override val root = tableview(persons) {
        column("ID", Person::id)
        column("Name", Person::name)
        column("Birthday", Person::birthday)
        column("Age", Person::age)
        columnResizePolicy = SmartResize.POLICY
    }*/

    override val root = GridPane()
    val compa = Comparatives()
    val arregloNumeros: Array<String> = arrayOf("12", "121", "3424")
    val hiPowerAnswer = FXCollections.observableArrayList<String>("Si", "No")

    private val persons = FXCollections.observableArrayList<Person>(
            Person(1, "Samantha Stuart", LocalDate.of(1981, 12, 4)),
            Person(2, "Tom Marks", LocalDate.of(2001, 1, 23)),
            Person(3, "Stuart Gills", LocalDate.of(1989, 5, 23)),
            Person(3, "Nicole Williams", LocalDate.of(1998, 8, 11))
    )

    private val valores = Valores()

    init {
        with(root) {

            tabpane {
                gridpaneConstraints {
                    hGrow = Priority.ALWAYS
                }
                tab("iCSConfig", GridPane()) {
                    //labels and buttons
                    vbox {
                        this += label("Alistamiento de iCSConfig")

                        this += Label("Clave Keystore *").apply {
                            hboxConstraints { margin = Insets(5.0) }
                        }
                        this += TextField().apply {
                            bind(valores.cadenaKeystoreProperty())
                            hboxConstraints { margin = Insets(5.0) }
                            useMaxWidth = true
                        }
                        this += Label("Clave Connectuser *").apply {
                            hboxConstraints { margin = Insets(5.0) }
                        }
                        this += TextField().apply {
                            bind(valores.cadenaConnectProperty())
                            hboxConstraints { margin = Insets(5.0) }
                            useMaxWidth = true
                        }
                        this += Label("Llave JDBC *").apply {
                            hboxConstraints { margin = Insets(5.0) }
                        }
                        this += TextField().apply {
                            bind(valores.cadenaPassProperty())
                            hboxConstraints { margin = Insets(5.0) }
                            useMaxWidth = true
                        }
                        this += Label("El uso y consecuencias de este software \nSon la responsabilidad del usuario final...").apply { hboxConstraints { margin = Insets(5.0) } }
                        this += Button("Cifrar").apply {
                            hboxConstraints { margin = Insets(5.0) }
                            setOnAction {
                                if (valores.cadenaKeystoreProperty().get().isNotEmpty() && valores.cadenaConnectProperty().get().isNotEmpty() && valores.cadenaKeystoreProperty().get().isNotEmpty()) {
                                    actuar()
                                }
                            }
                        }


                    }
                }

                tab("Cifrar", GridPane()) {
                    vbox {
                        this += label("Cifrado comun usando clave")

                        this += Label("Texto a Cifrar/Decifrar *").apply {
                            hboxConstraints { margin = Insets(5.0) }
                        }
                        this += TextField().apply {
                            bind(valores.cadenaKeystoreProperty())
                            hboxConstraints { margin = Insets(5.0) }
                            useMaxWidth = true
                        }
                        this += Label("Clave *").apply {
                            hboxConstraints { margin = Insets(5.0) }
                        }
                        this += TextField().apply {
                            bind(valores.cadenaConnectProperty())
                            hboxConstraints { margin = Insets(5.0) }
                            useMaxWidth = true
                        }
                        this += Label("¿Usar Cifrado de Alto Poder?").apply {
                            hboxConstraints { margin = Insets(5.0) }
                            this += ComboBox<String>().apply {
                                items = hiPowerAnswer
                            }
                            this += Label("El uso y consecuencias de este software \nSon la responsabilidad del usuario final...").apply { hboxConstraints { margin = Insets(5.0) } }
                            this += Button("Aplicar").apply {
                                hboxConstraints { margin = Insets(5.0) }
                                setOnAction {
                                    if (valores.cadenaKeystoreProperty().get().isNotEmpty() && valores.cadenaConnectProperty().get().isNotEmpty() && valores.cadenaKeystoreProperty().get().isNotEmpty()) {
                                        actuar()
                                    }
                                }
                            }
                        }
                    }
                }

                tab("Data", GridPane()) {
                    //the table
                    tableview(persons) {
                        column("ID", Person::idProperty)
                        column("Name", Person::nameProperty)
                        column("Birthday", Person::birthdayProperty)
                        column("Age", Person::ageProperty).cellFormat {
                            if (it < 18) {
                                style = "-fx-background-color:#8b0000; -fx-text-fill:white"
                                text = it.toString()
                            } else {
                                text = it.toString()
                            }
                        }
                    }
                }

                tab("Insert Pens", GridPane()) {
                    form {
                        fieldset("Pen Data") {
                            /*this += ButtonBar().apply {
                                // Set some properties on the ButtonBar
                                /*prefHeight = 20.0
                            prefWidth = 410.0*/
                                // Add any children inside the lambda to the button list of the ButtonBar
                                children(buttons) {
                                    button("Add Pen").setOnAction { persons.add(Person(persons.size + 1, "algo", LocalDate.now())) }
                                    button("Clear").setOnAction { compa.arrayParty(arregloNumeros) }
                                }
                            }*/
                            field("Brand") {
                                textfield()
                            }
                            field("Model") {
                                textfield()
                            }
                            field("Nib size") {
                                textfield()
                            }
                            field("Nib type") {
                                textfield()
                            }
                            field("Birthday") {
                                datepicker()
                            }
                        }
                        fieldset("Contact") {
                            field("Phone") {
                                textfield()
                            }
                            field("Email") {
                                textfield()
                            }
                        }
                        button("Commit") {
                            action { println("Wrote to database!") }
                        }
                    }
                }


            }
        }
    }

    private fun actuar() {
        val cypher = "Cadena cifrada"
        valores.cadenaConnectProperty().set(cypher + valores.cadenaPassProperty().get())
        valores.cadenaKeystoreProperty().set(cypher + valores.cadenaPassProperty().get())
    }

    class Person(id: Int, name: String, birthday: LocalDate) {
        private var id by property<Int>()
        fun idProperty() = getProperty(Person::id)

        private var name by property<String>()
        fun nameProperty() = getProperty(Person::name)

        private var birthday by property<LocalDate>()
        fun birthdayProperty() = getProperty(Person::birthday)

        //assume today is 2016-02-28
        private var age by property<Int>()

        fun ageProperty() = getProperty(Person::age)

        init {
            this.id = id
            this.name = name
            this.birthday = birthday
            this.age = Period.between(birthday, LocalDate.now()).years
            print("La edad registrada: ")
            println(age)
        }
    }

    class PenData(Id: Int, brand: String, model: String, nS: String, nT: String, birth: LocalDate) {
        private var id by property<Int>()
        fun idProperty() = getProperty(PenData::id)
        private var brand by property<String>()
        fun brandProperty() = getProperty(PenData::brand)
        private var model by property<String>()
        fun modelProperty() = getProperty(PenData::model)
        private var nibSize by property<String>()
        fun nibSizeProperty() = getProperty(PenData::nibSize)
        private var nibType by property<String>()
        fun nibTypeProperty() = getProperty(PenData::nibType)
        private var birthday by property<LocalDate>()
        fun birthdayProperty() = getProperty(PenData::birthday)

        init {
            this.id = Id
            this.brand = brand
            this.model = model
            this.nibSize = nS
            this.nibType = nT
            this.birthday = birth
        }
    }

    class Valores {
        private var cadenaConnect by property<String>()
        fun cadenaConnectProperty() = getProperty(Valores::cadenaConnect)
        private var cadenaKeystore by property<String>()
        fun cadenaKeystoreProperty() = getProperty(Valores::cadenaKeystore)
        private var cadenaPass by property<String>()
        fun cadenaPassProperty() = getProperty(Valores::cadenaPass)
        private var cadenaConnectSecured by property<String>()
        fun cadenaConnectSecuredProperty() = getProperty(Valores::cadenaConnectSecured)
        private var cadenaKeystoreSecured by property<String>()
        fun cadenaKeystoreSecuredProperty() = getProperty(Valores::cadenaKeystoreSecured)
    }

    class Comparatives {
        fun arrayParty(args: Array<String>) {
            val x = args[0].toInt()
            //Check if a number lies within a range:
            val y = 100
            if (x in 1 until y)
                println("OK")
            val tin = args.lastIndex
            //Iterate over a range:
            if (args.lastIndex > 0) for (a in 1..args.lastIndex) print("$a ")
            //Check if a number is out of range:
            println()
            val array = arrayListOf<String>()
            array.add("aaa")
            array.add("bbb")
            array.add("ccc")
            array.addAll(args)

            if (x !in 0 until array.size - 1)
                println("Out: array has only ${array.size} elements. x = ${x}")
            println(tin)

            //Check if a collection contains an object:
            if ("aaa" in array) // collection.contains(obj) is called
                println("Yes: array contains aaa")

            if ("ddd" in array) // collection.contains(obj) is called
                println("Yes: array contains ddd")
            else
                println("No: array doesn't contains ddd")

        }
    }
}