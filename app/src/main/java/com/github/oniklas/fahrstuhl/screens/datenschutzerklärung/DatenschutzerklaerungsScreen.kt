package com.github.oniklas.fahrstuhl.screens.datenschutzerklärung

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DatenschutzerklaerungsScreen (navController: NavHostController,
){
    val scroll = rememberScrollState(0)
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier, title = { Text(text = "Impressum") }, backgroundColor = MaterialTheme.colors.primary)
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(scroll),text =
            "Datenschutzerklärung\n" +
                    "Einleitung\n" +
                    "\n" +
                    "Mit der folgenden Datenschutzerklärung möchten wir Sie darüber aufklären, welche Arten Ihrer personenbezogenen Daten (nachfolgend auch kurz als \"Daten“ bezeichnet) wir zu welchen Zwecken und in welchem Umfang im Rahmen der Bereitstellung unserer Applikation verarbeiten.\n" +
                    "\n" +
                    "Die verwendeten Begriffe sind nicht geschlechtsspezifisch.\n" +
                    "\n" +
                    "Stand: 21. Oktober 2022\n" +
                    "Inhaltsübersicht\n" +
                    "\n" +
                    "    Einleitung\n" +
                    "    Verantwortlicher\n" +
                    "    Übersicht der Verarbeitungen\n" +
                    "    Maßgebliche Rechtsgrundlagen\n" +
                    "    Sicherheitsmaßnahmen\n" +
                    "    Löschung von Daten\n" +
                    "    Änderung und Aktualisierung der Datenschutzerklärung\n" +
                    "    Rechte der betroffenen Personen\n" +
                    "    Begriffsdefinitionen\n" +
                    "\n" +
                    "Verantwortlicher\n" +
                    "\n" +
                    "Niklas Bonkowski\n" +
                    "Salzburger Weg 11\n" +
                    "50858 Koeln, Germany\n" +
                    "E-Mail-Adresse:\n" +
                    "\n" +
                    "lunarcodingorg+Contact@gmail.com\n" +
                    "Impressum:\n" +
                    "\n" +
                    "Siehe impressum\n" +
                    "Übersicht der Verarbeitungen\n" +
                    "\n" +
                    "Die nachfolgende Übersicht fasst die Arten der verarbeiteten Daten und die Zwecke ihrer Verarbeitung zusammen und verweist auf die betroffenen Personen.\n" +
                    "Maßgebliche Rechtsgrundlagen\n" +
                    "\n" +
                    "Im Folgenden erhalten Sie eine Übersicht der Rechtsgrundlagen der DSGVO, auf deren Basis wir personenbezogene Daten verarbeiten. Bitte nehmen Sie zur Kenntnis, dass neben den Regelungen der DSGVO nationale Datenschutzvorgaben in Ihrem bzw. unserem Wohn- oder Sitzland gelten können. Sollten ferner im Einzelfall speziellere Rechtsgrundlagen maßgeblich sein, teilen wir Ihnen diese in der Datenschutzerklärung mit.\n" +
                    "\n" +
                    "Zusätzlich zu den Datenschutzregelungen der Datenschutz-Grundverordnung gelten nationale Regelungen zum Datenschutz in Deutschland. Hierzu gehört insbesondere das Gesetz zum Schutz vor Missbrauch personenbezogener Daten bei der Datenverarbeitung (Bundesdatenschutzgesetz – BDSG). Das BDSG enthält insbesondere Spezialregelungen zum Recht auf Auskunft, zum Recht auf Löschung, zum Widerspruchsrecht, zur Verarbeitung besonderer Kategorien personenbezogener Daten, zur Verarbeitung für andere Zwecke und zur Übermittlung sowie automatisierten Entscheidungsfindung im Einzelfall einschließlich Profiling. Des Weiteren regelt es die Datenverarbeitung für Zwecke des Beschäftigungsverhältnisses (§ 26 BDSG), insbesondere im Hinblick auf die Begründung, Durchführung oder Beendigung von Beschäftigungsverhältnissen sowie die Einwilligung von Beschäftigten. Ferner können Landesdatenschutzgesetze der einzelnen Bundesländer zur Anwendung gelangen.\n" +
                    "Sicherheitsmaßnahmen\n" +
                    "\n" +
                    "Wir treffen nach Maßgabe der gesetzlichen Vorgaben unter Berücksichtigung des Stands der Technik, der Implementierungskosten und der Art, des Umfangs, der Umstände und der Zwecke der Verarbeitung sowie der unterschiedlichen Eintrittswahrscheinlichkeiten und des Ausmaßes der Bedrohung der Rechte und Freiheiten natürlicher Personen geeignete technische und organisatorische Maßnahmen, um ein dem Risiko angemessenes Schutzniveau zu gewährleisten.\n" +
                    "\n" +
                    "Zu den Maßnahmen gehören insbesondere die Sicherung der Vertraulichkeit, Integrität und Verfügbarkeit von Daten durch Kontrolle des physischen und elektronischen Zugangs zu den Daten als auch des sie betreffenden Zugriffs, der Eingabe, der Weitergabe, der Sicherung der Verfügbarkeit und ihrer Trennung. Des Weiteren haben wir Verfahren eingerichtet, die eine Wahrnehmung von Betroffenenrechten, die Löschung von Daten und Reaktionen auf die Gefährdung der Daten gewährleisten. Ferner berücksichtigen wir den Schutz personenbezogener Daten bereits bei der Entwicklung bzw. Auswahl von Hardware, Software sowie Verfahren entsprechend dem Prinzip des Datenschutzes, durch Technikgestaltung und durch datenschutzfreundliche Voreinstellungen.\n" +
                    "Löschung von Daten\n" +
                    "\n" +
                    "Die von uns verarbeiteten Daten werden nach Maßgabe der gesetzlichen Vorgaben gelöscht, sobald deren zur Verarbeitung erlaubten Einwilligungen widerrufen werden oder sonstige Erlaubnisse entfallen (z.B. wenn der Zweck der Verarbeitung dieser Daten entfallen ist oder sie für den Zweck nicht erforderlich sind). Sofern die Daten nicht gelöscht werden, weil sie für andere und gesetzlich zulässige Zwecke erforderlich sind, wird deren Verarbeitung auf diese Zwecke beschränkt. D.h., die Daten werden gesperrt und nicht für andere Zwecke verarbeitet. Das gilt z.B. für Daten, die aus handels- oder steuerrechtlichen Gründen aufbewahrt werden müssen oder deren Speicherung zur Geltendmachung, Ausübung oder Verteidigung von Rechtsansprüchen oder zum Schutz der Rechte einer anderen natürlichen oder juristischen Person erforderlich ist.\n" +
                    "\n" +
                    "Unsere Datenschutzhinweise können ferner weitere Angaben zu der Aufbewahrung und Löschung von Daten beinhalten, die für die jeweiligen Verarbeitungen vorrangig gelten.\n" +
                    "Änderung und Aktualisierung der Datenschutzerklärung\n" +
                    "\n" +
                    "Wir bitten Sie, sich regelmäßig über den Inhalt unserer Datenschutzerklärung zu informieren. Wir passen die Datenschutzerklärung an, sobald die Änderungen der von uns durchgeführten Datenverarbeitungen dies erforderlich machen. Wir informieren Sie, sobald durch die Änderungen eine Mitwirkungshandlung Ihrerseits (z.B. Einwilligung) oder eine sonstige individuelle Benachrichtigung erforderlich wird.\n" +
                    "\n" +
                    "Sofern wir in dieser Datenschutzerklärung Adressen und Kontaktinformationen von Unternehmen und Organisationen angeben, bitten wir zu beachten, dass die Adressen sich über die Zeit ändern können und bitten die Angaben vor Kontaktaufnahme zu prüfen.\n" +
                    "Rechte der betroffenen Personen\n" +
                    "\n" +
                    "Ihnen stehen als Betroffene nach der DSGVO verschiedene Rechte zu, die sich insbesondere aus Art. 15 bis 21 DSGVO ergeben:\n" +
                    "\n" +
                    "    Widerspruchsrecht: Sie haben das Recht, aus Gründen, die sich aus Ihrer besonderen Situation ergeben, jederzeit gegen die Verarbeitung der Sie betreffenden personenbezogenen Daten, die aufgrund von Art. 6 Abs. 1 lit. e oder f DSGVO erfolgt, Widerspruch einzulegen; dies gilt auch für ein auf diese Bestimmungen gestütztes Profiling. Werden die Sie betreffenden personenbezogenen Daten verarbeitet, um Direktwerbung zu betreiben, haben Sie das Recht, jederzeit Widerspruch gegen die Verarbeitung der Sie betreffenden personenbezogenen Daten zum Zwecke derartiger Werbung einzulegen; dies gilt auch für das Profiling, soweit es mit solcher Direktwerbung in Verbindung steht.\n" +
                    "    Widerrufsrecht bei Einwilligungen: Sie haben das Recht, erteilte Einwilligungen jederzeit zu widerrufen.\n" +
                    "    Auskunftsrecht: Sie haben das Recht, eine Bestätigung darüber zu verlangen, ob betreffende Daten verarbeitet werden und auf Auskunft über diese Daten sowie auf weitere Informationen und Kopie der Daten entsprechend den gesetzlichen Vorgaben.\n" +
                    "    Recht auf Berichtigung: Sie haben entsprechend den gesetzlichen Vorgaben das Recht, die Vervollständigung der Sie betreffenden Daten oder die Berichtigung der Sie betreffenden unrichtigen Daten zu verlangen.\n" +
                    "    Recht auf Löschung und Einschränkung der Verarbeitung: Sie haben nach Maßgabe der gesetzlichen Vorgaben das Recht, zu verlangen, dass Sie betreffende Daten unverzüglich gelöscht werden, bzw. alternativ nach Maßgabe der gesetzlichen Vorgaben eine Einschränkung der Verarbeitung der Daten zu verlangen.\n" +
                    "    Recht auf Datenübertragbarkeit: Sie haben das Recht, Sie betreffende Daten, die Sie uns bereitgestellt haben, nach Maßgabe der gesetzlichen Vorgaben in einem strukturierten, gängigen und maschinenlesbaren Format zu erhalten oder deren Übermittlung an einen anderen Verantwortlichen zu fordern.\n" +
                    "    Beschwerde bei Aufsichtsbehörde: Sie haben unbeschadet eines anderweitigen verwaltungsrechtlichen oder gerichtlichen Rechtsbehelfs das Recht auf Beschwerde bei einer Aufsichtsbehörde, insbesondere in dem Mitgliedstaat ihres gewöhnlichen Aufenthaltsorts, ihres Arbeitsplatzes oder des Orts des mutmaßlichen Verstoßes, wenn Sie der Ansicht sind, dass die Verarbeitung der Sie betreffenden personenbezogenen Daten gegen die Vorgaben der DSGVO verstößt.\n" +
                    "\n" +
                    "Begriffsdefinitionen\n" +
                    "\n" +
                    "In diesem Abschnitt erhalten Sie eine Übersicht über die in dieser Datenschutzerklärung verwendeten Begrifflichkeiten. Viele der Begriffe sind dem Gesetz entnommen und vor allem im Art. 4 DSGVO definiert. Die gesetzlichen Definitionen sind verbindlich. Die nachfolgenden Erläuterungen sollen dagegen vor allem dem Verständnis dienen. Die Begriffe sind alphabetisch sortiert.\n" +
                    "\n" +
                    "    Personenbezogene Daten: \"Personenbezogene Daten“ sind alle Informationen, die sich auf eine identifizierte oder identifizierbare natürliche Person (im Folgenden \"betroffene Person“) beziehen; als identifizierbar wird eine natürliche Person angesehen, die direkt oder indirekt, insbesondere mittels Zuordnung zu einer Kennung wie einem Namen, zu einer Kennnummer, zu Standortdaten, zu einer Online-Kennung (z.B. Cookie) oder zu einem oder mehreren besonderen Merkmalen identifiziert werden kann, die Ausdruck der physischen, physiologischen, genetischen, psychischen, wirtschaftlichen, kulturellen oder sozialen Identität dieser natürlichen Person sind.\n" +
                    "    Verantwortlicher: Als \"Verantwortlicher“ wird die natürliche oder juristische Person, Behörde, Einrichtung oder andere Stelle, die allein oder gemeinsam mit anderen über die Zwecke und Mittel der Verarbeitung von personenbezogenen Daten entscheidet, bezeichnet.\n" +
                    "    Verarbeitung: \"Verarbeitung\" ist jeder mit oder ohne Hilfe automatisierter Verfahren ausgeführte Vorgang oder jede solche Vorgangsreihe im Zusammenhang mit personenbezogenen Daten. Der Begriff reicht weit und umfasst praktisch jeden Umgang mit Daten, sei es das Erheben, das Auswerten, das Speichern, das Übermitteln oder das Löschen. \n" +
                    "\n" +
                    "Vom Appinhaber angepasst\n" +
                    "Erstellt mit kostenlosem Datenschutz-Generator.de von Dr. Thomas Schwenke"


            )
        }
    }
}
