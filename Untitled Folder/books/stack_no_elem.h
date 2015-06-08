/**
*	http://trekto.info/asd
*/

#include <iostream>
#include <fstream>					// нужна за работа с файлове
#include <cstdlib>					// нужна за използване на exit()

using namespace std;

template <class T>
class Stack {
private:
	Elem<T>* top;					// указател към върха на стека
	void copyStack(const Stack& aStack);		// копира aStack в текущия стек
	void printToStream(ostream &os) const;		// извежда стека в потока os

public:
	Stack();					// конструктор по подразбиране
	Stack(const Stack& aStack);			// копиращ конструктор
	~Stack();					// деструктор
	void push(const T aData);			// вмъква елемент в стека
	T pop();					// измъква елемент от стека
	bool pop(T &aData);				// измъква елемент от стека. Ако е празен връща 0
	bool peek(T &aData);				// като bool pop(T &aData); но не премахва елемента
	void removeAll();				// изчиства стека
	void print() const;				// отпечатва стека на екрана
	void writeToFile(const char* aFileName) const;	// записва стека в двоичен файл
	void readFile(const char* aFileName);		// прочита стека от двоичен файл
	Stack<T> &operator = (const Stack &aStack);	// оператор за присвояване
	friend ostream &operator << (ostream &os, const Stack &aStack) {	// оператор за извеждане
		aStack.printToStream(os);
		return os;
	}
};

template <class T>
Stack<T>:: Stack() {					// конструктор по подразбиране
	top = NULL;
}

template <class T>
Stack<T>:: Stack(const Stack& aStack) {			// копиращ конструктор
	top = NULL;
	copyStack(aStack);
}

template <class T>
void Stack<T>:: copyStack(const Stack& aStack) {	// копира aStack в текущия стек
	if(!aStack.top) return;				// ако стекът е празен няма нищо за копиране

	Elem<T>* p = aStack.top;			// тръгваме от върха
	Elem<T>* bottom = top = new Elem<T>;		// декларираме помощен указател към края (дъното)
							// и заделяме памет за първия елемент
							// трябва да добавяме елементите в края
							// иначе ще са в обърнат ред
	top->data = p->data;				// копираме данните за първия елемент
	p = p->next;					// преминаваме към втория

	while(p) {					// докато не сме стигнали края на стека
		bottom->next = new Elem<T>;		// заделяме памет за новия елемент
		bottom = bottom->next;			// насочваме края към него
		bottom->data = p->data;			// копираме данните
		p = p->next;				// преминаваме към следващия
	}

	bottom->next = NULL;				// насочваме 'следващия' на последния към NULL
}

template <class T>
Stack<T> &Stack<T>:: operator = (const Stack& aStack) {	// оператор за присвояване
	if(this != &aStack) {				// ако параметърът не е текущият
		removeAll();
		copyStack(aStack);
	}
	return *this;					// връщаме текущия стек
}

template <class T>
Stack<T>:: ~Stack() {					// деструктор
	removeAll();
}

template <class T>
void Stack<T>:: push(T aData) {				// вмъква елемент в стека
	Elem<T>* temp = top;				// временен указател към първия елемент (върха)
	top = new Elem<T>;				// заделяме памет за новия елемент
	top->data = aData;				// присвояваме данните
	top->next = temp;				// насочваме 'следващия' към предишния първи
}

template <class T>
T Stack<T>:: pop() {					// измъква елемент от стека
	if(!top) {					// ако стекът е празен
		cerr << "Грешка: стекът е празен!\n";	// извеждаме съобщение за грешка
		exit(1);				// излизаме от програмата
	}

	T data = top->data;				// запазваме данните, които са на върха
	Elem<T>* temp = top;				// насочваме временен указател към върха
	top = top->next;				// нов връх става вторият елемент
	delete temp;					// изтриваме първия
	return data;					// връщаме данните
}

template <class T>
bool Stack<T>:: pop(T &aData) {				// измъква елемент от стека. Ако е празен връща 0
	if(!top) return 0;				// ако стекът е празен връщаме 0 (false)

	aData = top->data;				// присвояваме данните на параметъра псевдоним
	Elem<T>* temp = top;				// насочваме временен указател към върха
	top = top->next;				// нов връх става вторият елемент
	delete temp;					// изтриваме първия
	return 1;					// връщаме 1 (true)
}

template <class T>
bool Stack<T>:: peek(T &aData) {			// като bool pop(T &aData); но не премахва елемента
	if(!top) return 0;				// ако стекът е празен връщаме 0 (false)

	aData = top->data;				// присвояваме данните на параметъра псевдоним
	return 1;					// връщаме 1 (true)
}

template <class T>
void Stack<T>:: removeAll() {				// изчиства стека
	Elem<T>* temp;					// декларираме си временен указател
	while(top) {					// докато има нещо в стека
		temp = top;				// насочваме временния указател към върха
		top = top->next;			// нов връх става вторият елемент
		delete temp;				// изтриваме стария връх
	}
}

template <class T>
void Stack<T>:: print() const {				// отпечатва стека на екрана
	printToStream(cout);				// отпечатваме стека в потока cout
}

template <class T>
void Stack<T>:: printToStream(ostream &os) const {	// извежда стека в потока os
	Elem<T>* p = top;				// указател за обхождане
	os << "\n";
	while(p) {					// докато не стигнем дъното на стека
		os << "| " << p->data << " |\n";	// отпечатваме данните, подходящо форматирани
		p = p->next;				// преминаваме към следващия елемент
	}
	os << "-----\n";
}

template <class T>
void Stack<T>:: writeToFile(const char* aFileName) const {	// записва стека в двоичен файл
	ofstream f(aFileName, ios::binary);		// декларираме и отваряме двоичен файл за запис
	if (!f) {					// ако има някаква грешка
		cerr << "Възникна входно/изходна грешка!" << endl;
		return;
	}

	Elem<T>* p = top;				// насочваме указателя за обхождане в началото
	while(p){					// докато не е достигнато дъното на стека
		f.write((char*) &(p->data), sizeof(T));	// записваме стойността на текущия ел. във файла
		p = p->next;				// и преминаваме към следващия елемент
	}
	f.close();					// затваряме файла
}

template <class T>
void Stack<T>:: readFile(const char* aFileName) {	// прочита стека от двоичен файл
	ifstream f(aFileName, ios::binary);		// декларираме и отваряме двоичен файл за четене
	if (!f) {					// ако има някаква грешка
		cerr << "Възникна входно/изходна грешка!" << endl;
		return;
	}

	removeAll();					// изтриваме всички елементи на стека
	Elem<T>* temp = top = new Elem<T>;		// заделяме памет за първия елемент
	Elem<T>* bottom = NULL;				// временен указател към дъното

	while (f.read((char*) &(temp->data), sizeof(T))) {	// докато успешно четем от файла
		bottom = temp;				// насочваме края към новия елемент
		temp = bottom->next = new Elem<T>;	// заделяме памет за следващия
	}

	delete temp;					// освобождаваме последно заделената памет
							// тъй като последното четене е било неуспешно
	if(bottom) bottom->next = NULL;			// ако сме прочели нещо, указваме края
	else top = NULL;				// иначе стекът остава празен

	// Горните два реда могат да се напишат по-елегантно с тринарния оператор:
	// (bottom ? bottom->next : top) = NULL;

	f.close();					// затваряме файла
}
