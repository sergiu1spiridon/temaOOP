Spiridon Sergiu 322CA


	Clasa principala este clasa Database, care are un field input(va contine o copie a 
	inputului din main), un hashtable pentru useri, un hashtable pentru videos(am ales aceasta
	structura de date pentru o accesare mai rapida a obiectelor), un ArrayList care stocheaza
	actorii si un ArrayList de videos databaseOrderedArray care tine videoclipurile in ordinea in
	care au fost introduse in baza de date. In main apelez constructorul pentru clasa Database,
	cu parametrul input, apoi apelez metoda initFields care pune in datele di input in baza de date
	si apoi interpreteaza comenzile.

	Pentru comanda "favorite" a userilor am creeat clasa FavoriteCommand care contine o metoda 
	"public int addFavorite(final User user, final Video video)" unde se verifica daca userul a
	vazut videoclipul si nu l-a adaugat deja la favorite inainte de a-l adauga in lista sa de videoclipuri
	favorite. Clasa Video are un camp "favored" in care se retine de cate ori a fost adaugat intr-o lista
	de favorite; daca adaugarea se produce cu succes, marim favored de la videoclipul respectiv.

	Comanda "view" are asociata clasa ViewComand, care pune videoclipul in lista de videoclipuri visualizate
	ale userului, impreuna cu noul numar de visualizari. Clasa ViewedVideos este utilizata pentru a retine
	numarul total de visualizari ale fiecarui videoclip din baza de date.

	Pentru comanda "rating" se utilizeaza clasa RatingCommand, care contine metoda addRating, unde se verifica
	daca videoclipul este vizualizat, nu a primit un rating de la user si apoi i se adauga ratingul. Medota
	addRating are ca parametrii use, video si grade pentru un movie si se adauga la lista de parametrii si
	numarul sezonului pentru un serial.

	Query-ul pentru actori cu actionType-ul "average" utilizeaza clasa Average cu metoda getRatingList, care
	formeaza o lista de actori sortata ascendent sau decendent dupa ratingul mediu al filmelor in care au jucat
	si nume, daca ratingurile sunt egale. 
	Sortarea se aseamana pentru amjoritatea query-urilor care cer o lista de obiecte sortate. Se creeaza o lista
	inlantuita de obiectul respectiv; parcurgem lista de obiecte si pentru fiecare in parte se compara cu elementele din
	lista noua pana cand gasim un element mai mic sau mare decat el(in functie de ordinea in care trebuie sortat) si
	se adauga obiectul in lista noua.

	Query-ul pentru actori cu actionType-ul "awards" primeste o lista de awards si apoi cheama metoda getAwardsList din
	clasa Awards. Se verifica daca videoclipul dat are in lista de awards a sa toata premiile cerute in query, iar daca le are
	se face sortarea comparand numarul total de premii.

	Query-ul pentru actori cu actionType-ul "filterDescription" foloseste metoda getFileredActors din clasa FilterDescrition.
	Se primeste o lista de cuvinte care trebuie sa se regaseasca in descrierea actorlui. Pentru fecare actor se imparte
	in cuvinte descrierea lui(separatorii:" ,.-") se fac toate literele mici si apoi verificam daca toate cuvintele din
	query se regasesc in lista de cuvinte din descriere inainte de a pune actorul in lista sortata.

	Pentru query-ul de useri se apeleaza medota getUserList din clasa NumberOfRatingsUser, care sorteaza lista de useri dupa
	numarul de ratinguri pe care l-au dat la filme si seriale. NumberOfRatings este un camp in clasa User care se mareste de
	fiecare data cand userul reuseste sa dea un rating.

	Pentru query-urile de videos singura diferenta intre clase de tipul MovieFavorite-ShowFavorite este verificarea clasei
	din care face parte instanta.

	Query-ul pentru filme/seriale de favorite utilizeaza clasa MovieFavorite, respectiv ShowFavorite care au metode care
	returneaza lista sortata dupa campul favored din clasa video. Inainte de sortare se verifica si filtrele care pot fi date
	in query(yearFilter si genreFilter).

	Query-ul pentru filme/seriale de longest apeleaza metode din MovieLongest/ShowLongest unde sortarea si verificarea filtrelor
	se face ca pentru cazul de favorite, dar in functie de lungimea videoclipului.

	Query-ul pentru MovieMostViewed/ShowMostViewed foloseste pentru sortare clasa ViewedVideos, in care se retine numarul total de
	vizualizari ale videoclipurilor.

	Pentru query-ul MovieRating/ShowRating se face sortarea si verificarea filtrelor ca si la favorite, dar se compara ratingul pe
	care in are videoclipul.

	Pentru recomandations nu se foloseste in metode Hashablelul videosArray, ci dataBaseVideoArray pentru ca avem nevoie de
	ordinea in care au fost introduse videoclipurile in baza de date.

	Recomandarea standard se foloseste de clasa Standard, metoda getStandard, care returneaza primul videoclip din baza de date
	nevazut de user.

	Recomandarea BestUnseen foloseste clasa BestUnseen, metoda getBestUnseen, unde se gaseste videoclipul care are cel mai mare rating
	si il returneaza.

	Recomandare Favorite utilizeaza metoda getFavorite din clasa Favorite, packageul recomandation. Se creeaza o lista sortata descrescator
	dupa campul favored si apoi se returneaza primul videoclip nevisualizat.

	Recomandarea Popular apeleaza intai metoda createGenreArray din recomandations.Popular care creeaza o lista de genuri sortate
	dupa numarul de visualizari al videoclipurilor cu acel gen in lista. Dupa ce se formeaza lista de genuri sortate se apeleaza
	getPopular care parcurge lista de genuri si cauta primul videoclip nevizualizat.

	Recomandarea search parcurge Hashtableul de videoclipuri si returneaza o lista cu toate videoclipurile nevizualizate de user
	care apartin genului dorit. 