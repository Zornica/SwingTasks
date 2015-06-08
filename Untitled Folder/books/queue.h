/**
*	http://trekto.info/asd
*/

#include <iostream>
#include <fstream>					// нужна за работа с файлове
#include <cstdlib>					// нужна за използване на exit()

using namespace std;

template <class T>
struct Elem {
	T data;						// данни
	Elem<T>* next;					// указател към следващия елемент
};

template <class T>
class Queue {
private:
	Elem<T>* pStart;				// указател към началото на опашката
	Elem<T>* pEnd;					// указател към края
	unsigned size;					// броят на елементите в опашката
	void copyQueue(const Queue<T> &aQueue);		// помощен метод за копиране на опашката
	void printToStream(ostream &os) const;		// извежда опашката в потока os

public:
	Queue();					// конструктор по подразбиране
	Queue(const Queue<T> &aQueue);			// копиращ конструктор
	~Queue();					// деструктор
	void push(const T aData);			// вмъква елемент в края
	T pop();					// измъква елемент от опашката
	bool pop(T &aData);				// измъква елемент от опашката. Връща 0 ако е празна
	unsigned getSize() const;			// връща броя на елементите в опашката
	void removeAll();				// премахва всички елементи от опашката
	void writeToFile(const char* aFileName) const;	// записва опашката в двоичен файл
	void readFile(const char* aFileName);		// прочита опашката от двоичен файл
	Queue<T> &operator = (const Queue &aQueue);	// оператор за присвояване
	friend ostream& operator << (ostream& os, Queue& aQueue) {	// оператор за извеждане
		aQueue.printToStream(os);
		return os;
	}
};

template <class T>
Queue<T>:: Queue() {					// конструктор по подразбиране
	pStart = pEnd = NULL;				// насочваме указателите към NULL
	size = 0;					// зануляваме броя на елементите
}

template <class T>
Queue<T>:: Queue(const Queue<T> &aQueue) {		// копиращ конструктор
	pStart = pEnd = NULL;				// насочваме указателите към NULL
	size = 0;					// зануляваме броя на елементите
	copyQueue(aQueue);				// използваме помощния метод за копиране на опашка
}

template <class T>
Queue<T>:: ~Queue() {					// деструктор
	removeAll();					// изтриваме всички елементи
}

template <class T>
void Queue<T>:: copyQueue(const Queue<T> &aQueue) {	// копиращ конструктор
	Elem<T>* p = aQueue.pStart;			// тръгваме от началото
	if(!p)	return;					// ако опашката е празна излизаме
	else pStart = pEnd = new Elem<T>;		// ако не е празна заделяме памет за 1-я елемент

	while(true) {					// от цикъла излизаме с break, по-долу
		pEnd->data = p->data;			// копираме данните на текущия елемент
		size++;					// увеличаваме броя на елементите
		p = p->next;				// преминаваме към следващия елемент
		if(!p) {				// ако няма такъв
			pEnd->next = NULL;		// насочваме края към NULL
			break;				// и спираме
		}
		pEnd->next = new Elem<T>;		// заделяме памет и насочваме 'следващия' на текущия
							// последен да сочи към нея,
		pEnd = pEnd->next;			// след което насочваме последния да сочи към същата памет
	}
}

template <class T>
void Queue<T>:: push(const T aData) {			// вмъква елемент в края
	if(!pStart) {					// ако опашката е празна
		pStart = pEnd = new Elem<T>;		// заделяме памет за новия елемент
	} else {					// ако не е празна
		pEnd->next = new Elem<T>;		// заделяме памет за новия елемент
		pEnd = pEnd->next;			// преместваме края да сочи към нея
	}

	pEnd->data = aData;				// копираме данните
	pEnd->next = NULL;				// правим 'следващия' на последния да е NULL
	size++;						// увеличаваме броя на елементите
}

template <class T>
T Queue<T>:: pop() {					// измъква елемент от опашката
	if(!pStart) {
		cerr << "Грешка:  опашката е празна!" << endl;
		exit(1);
	}

	T data = pStart->data;				// запазваме данните, които са в първия елемент
	Elem<T>* temp = pStart;				// насочваме помощен указател към него
	pStart = pStart->next;				// насочваме началото към 'следващия' му
	delete temp;					// изтриваме първия
	if(!pStart) pEnd = NULL;			// ако опашката е станала празна - края към NULL
	size--;						// намаляме броя на елементите
	return data;					// връщаме данните
}

template <class T>
bool Queue<T>:: pop(T &aData) {				// измъква елемент от опашката. Връща 0 ако е празна
	if(!pStart) return 0;

	aData = pStart->data;				// присвояваме данните на параметъра
	Elem<T>* temp = pStart;				// насочваме помощен указател към първия елемент
	pStart = pStart->next;				// насочваме началото към 'следващия' му
	delete temp;					// изтриваме първия
	if(!pStart) pEnd = NULL;			// ако опашката е станала празна - края към NULL
	size--;						// намаляме броя на елементите
	return 1;					// връщаме true
}

template <class T>
void Queue<T>:: removeAll() {				// премахва всички елементи от опашката
	while(pStart) {					// докато има нещо в опашката
		pEnd = pStart;				// насочваме pEnd към първия елемент
		pStart = pStart->next;			// насочваме началото към втория
		delete pEnd;				// изтриваме първия
	}
	pEnd = NULL;					// насочваме pEnd натам,
							// накъдето вече сочи и pStart: NULL
	size = 0;					// зануляваме броя на елементите
}

template <class T>
void Queue<T>:: writeToFile(const char* aFileName) const {	// записва опашката в двоичен файл
	ofstream f(aFileName, ios::binary);		// декларираме и отваряме двоичен файл за запис
	if (!f) {					// ако има някаква грешка
		cerr << "Възникна входно/изходна грешка!" << endl;
		return;
	}

	Elem<T>* p = pStart;				// насочваме указателя за обхождане в началото
	while(p){					// докато не е достигнат края на опашката
		f.write((char*) &(p->data), sizeof(T));	// записваме стойността на текущия ел. във файла
		p = p->next;				// и преминаваме към следващия елемент
	}
	f.close();					// затваряме файла
}

template <class T>
void Queue<T>:: readFile(const char* aFileName) {	// прочита опашката от двоичен файл
	ifstream f(aFileName, ios::binary);		// декларираме и отваряме двоичен файл за четене
	if (!f) {					// ако има някаква грешка
		cerr << "Възникна входно/изходна грешка!" << endl;
		return;
	}

	removeAll();					// изтриваме всички елементи на опашката
	Elem<T>* temp = pStart = new Elem<T>;		// заделяме памет за първия елемент

	while (f.read((char*) &(temp->data), sizeof(T))) {	// докато успешно четем от файла
		pEnd = temp;				// насочваме края към новия елемнт
		temp = pEnd->next = new Elem<T>;	// заделяме памет за следващия
		size++;					// увеличаваме броя на елементите
	}

	delete temp;					// освобождаваме последно заделената памет
							// тъй като последното четене е било неуспешно
	(pEnd ? pEnd->next : pStart) = NULL;		// ако сме прочели нещо, указваме края
							// иначе опашката остава празна
	f.close();					// затваряме файла
}

template <class T>
Queue<T> &Queue<T>:: operator = (const Queue &aQueue) {	// оператор за присвояване
	if(this != &aQueue) {				// ако не се опитваме да копираме опашката в себе си
		removeAll();				// изтриваме всички елементи
		copyQueue(aQueue);			// копираме опашката
	}
	return *this;					// връщаме текущия обект
}

template <class T>
void Queue<T>:: printToStream(ostream &os) const {	// извежда опашката в потока os
	Elem<T>* p = pStart;				// указател за обхождане

	// форматираме изхода, за да е по четим
	os << " размер: " << size << "\n\t<";
	int i = 0, n = 5;

	while(p || i < n) {
		os << "--";
		if(p) p = p->next;
		i++;
	}

	os << "\n\t";
	p = pStart;

	while(p) {					// докато не стигнем края на опашката
		os << p->data << " ";			// отпечатваме данни
		p = p->next;				// преминаваме към следващия елемент
	}

	os << "\n\t<";
	p = pStart;
	i = 0;

	while(p || i < n) {
		os << "--";
		if(p) p = p->next;
		i++;
	}

	os << "\n";
}

template <class T>
unsigned Queue<T>:: getSize() const {			// връща броя на елементите в опашката
	return size;
}