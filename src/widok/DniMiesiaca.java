package widok;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.Date;

//import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class DniMiesiaca {
	private int dni[][] = new int[6][7];
	private JLabel dniEtykiety[][] = new JLabel[6][7];
	private int miesiace[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private Calendar calendar;
	public int rok, miesiac;

	public DniMiesiaca(Date data) {
		ustawDate(data);
		// this.data = data;
		// this.rok = data.getYear() + 1900;
		// this.miesiac = data.getMonth();
		// if (this.rok % 4 == 0)
		// miesiace[1] = 29;
	}

	private void wypelnijTabele() {
		// zerujemy tablice
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dni[i][j] = 0;
			}
		}

		int poczatkowyDzienTygodnia = dzienTygodnia();
		int dniWMiesiacu = miesiace[miesiac];
		int dzien = 1;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				// w pierwszym tygodniu pomin dni z poprzedniego miesiaca
				if (i == 0 && j >= poczatkowyDzienTygodnia || i > 0) {
					// jak wpiszemy ostatni dzien miesiaca przerywamy wpisywanie
					if (dzien <= dniWMiesiacu) {
						dni[i][j] = dzien++;
					}
				}
			}
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				Point punkt = new Point(j * 38, i * 30);
				int szerokosc = 37, wysokosc = 29;
				if (dni[i][j] == 0) {
					dniEtykiety[i][j] = stworzEtykiete(" ", punkt, szerokosc,
							wysokosc);
				} else {
					dniEtykiety[i][j] = stworzEtykiete(
							Integer.toString(dni[i][j]), punkt, szerokosc,
							wysokosc);
				}
				//dniEtykiety[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				
				// if (ObslugaKalendarza.aktualnyRok == aktualnaData.getYear() +
				// 1900
				// &&
				// ObslugaKalendarza.miesiace[ObslugaKalendarza.aktualnyMiesiac]
				// == ObslugaKalendarza.miesiace[aktualnaData
				// .getMonth()]
				// && (dzis.zwrocTabele(i, j) == aktualnaData.getDate()))
				// dniLabel[i][j].setBorder(BorderFactory.createLineBorder(
				// Color.RED, 1));
				// else
				// dniLabel[i][j].setBorder(null);

				// contentPane.add(dniLabel[i][j]);
			}
		}

		// for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 7; j++) {
		// System.out.print(dni[i][j] + " ");
		// }
		// System.out.print("\n");
		// }
	}

	private JLabel stworzEtykiete(String tekst, Point punkt, int szerokosc, int wysokosc) {
		
		JLabel etykieta = new JLabel();
		etykieta.setHorizontalAlignment(JLabel.CENTER);
		etykieta.setVerticalAlignment(JLabel.CENTER);
		etykieta.setText(tekst);
		etykieta.setBounds(new Rectangle(punkt, new Dimension(szerokosc,wysokosc)));

		return etykieta;
	}

	private int dzienTygodnia() {
		// DAY_OF_WEEK zwraca liczbe od 1 (niedziela) do 7 (sobota)
		// �eby 0 to by� poniedzia�ek, to przesuwamy wynik o 5 i modulo 7
		return (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
	}

	public void ustawDate(Date data) {
		// nie ma publicznego konstruktora, korzystamy ze statycznej metody
		calendar = Calendar.getInstance();
		calendar.setTime(data);
		this.rok = calendar.get(Calendar.YEAR);
		this.miesiac = calendar.get(Calendar.MONTH);
		if ((this.rok % 4 == 0 && this.rok % 100 != 0) || this.rok % 400 == 0) {
			miesiace[1] = 29;
		} else {
			miesiace[1] = 28;
		}

		wypelnijTabele();
	}

	public int zwrocMiesiac() {
		return calendar.get(Calendar.MONTH);
	}

	public int zwrocTabele(int i, int j) {
		return this.dni[i][j];
	}

	public JLabel zwrocEtykiete(int i, int j) {
		return this.dniEtykiety[i][j];
	}
	
	
}
