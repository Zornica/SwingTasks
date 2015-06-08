/**
*	http://trekto.info/asd
*/

#include <iostream>
#include "queue.h"
#include "stack_no_elem.h"

#define N 16
#define NUMBER 10
#define OPERATOR_MIN 1
#define OPERATOR_MAX 5

using namespace std;

struct Token {							// структура съдържаща символ (token) и приоритета му
	char token;
	int priority;
};

Token tokens[N];						// масив със символите от езика

void prepare();							// задава символите и приоритета им
int priority(char t);						// връща приоритета на подадения символ
inline bool isOperator(char t);					// проверява приоритета на подадения символ
double oper(char op, double n1, double n2);			// прилага оператора op върху операндите n1 и n2
Queue<char> convert(Queue<char> expr);				// преобразува exrp в обратен полски запис и го връща
double calculate(Queue<char> polish);				// изчислява обратния полски запис и връща стойността
Queue<char> read();						// прочита аритметичен израз и го връща

int main() {
	prepare();
	cout << "Резултат: " << calculate(convert(read())) << endl;	// малко функционално програмиране
	return 0;
	
	// 1+2+2*3*4+6*2+1/2 = 39.5
	// 1+(2+2)*3*4+6*2+1/2 = 61.5
	// (1+(2+2)*3*(4+6)*2+1)/2 = 121
	// 3 + 4 - (3 * 5) / 2 = -0.5		3 4 + 3 5 * 2 / -
}

void prepare() {						// задава символите и приоритета им
	int i = 0;
	tokens[i].token = '0'; tokens[i++].priority = NUMBER;
	tokens[i].token = '1'; tokens[i++].priority = NUMBER;
	tokens[i].token = '2'; tokens[i++].priority = NUMBER;
	tokens[i].token = '3'; tokens[i++].priority = NUMBER;
	tokens[i].token = '4'; tokens[i++].priority = NUMBER;
	tokens[i].token = '5'; tokens[i++].priority = NUMBER;
	tokens[i].token = '6'; tokens[i++].priority = NUMBER;
	tokens[i].token = '7'; tokens[i++].priority = NUMBER;
	tokens[i].token = '8'; tokens[i++].priority = NUMBER;
	tokens[i].token = '9'; tokens[i++].priority = NUMBER;
	
	tokens[i].token = '+'; tokens[i++].priority = 1;калкулатор с
	tokens[i].token = '-'; tokens[i++].priority = 1;
	tokens[i].token = '*'; tokens[i++].priority = 5;
	tokens[i].token = '/'; tokens[i++].priority = 5;
	
	tokens[i].token = '('; tokens[i++].priority = OPERATOR_MAX + 1;
	tokens[i].token = ')'; tokens[i++].priority = OPERATOR_MAX + 1;
}

int priority(char t) {						// връща приоритета на подадения символ
	for(int i = 0; i < N; i++)
		if (t == tokens[i].token) return tokens[i].priority;
	cerr << "Грешка: Символът '" << t << "' не е от езика!\n";
	exit(1);
}

inline bool isOperator(char t) {				// проверява приоритета на подадения символ
								// ключовата дума inline, препоръчва на компилатора
								// директно да замени срещанията на isOperator с
								// тялото на функцията вместо да прави обръщение към нея
	return (priority(t) >= OPERATOR_MIN && priority(t) <= OPERATOR_MAX);	// връща 'истина' ако t е оператор
}

double oper(char op, double n1, double n2) {			// прилага оператора op върху операндите n1 и n2
	if(op == '+') return n1 + n2;				// и връща резултата
	if(op == '-') return n1 - n2;
	if(op == '*') return n1 * n2;
	if(op == '/') return n1 / n2;
	cerr << "Грешка: Неразпознат оператор!\n";
	exit(1);
}

Queue<char> convert(Queue<char> expr) {				// преобразува exrp в обратен полски запис и го връща
	cout << "Аритметичният израз: " << expr << endl;

	Queue<char> polish;					// тук ще записваме обр. полски запис
	Stack<char> stack;

	char t;
	char t2;
	while(expr.pop(t)) {					// докато има символи в израза
		if(priority(t) == NUMBER)			// ако символът е операнд (число)
			polish.push(t);				// го поставяме директно в обр. полски запис
		else if(t == '(')				// ако е отваряща скоба
			stack.push(t);				// я поставяме в стека
		else if(isOperator(t)) {			// ако е оператор
			while(stack.peek(t2) &&			// докато на върха на стека има символ
				isOperator(t2) &&		// и той е оператор
				priority(t2) >= priority(t))	// с по-голям или равен приоритет
					polish.push(stack.pop());// гo поставяме в обратния полски запис
			stack.push(t);				// добавяме новия оператор в стека
		} else if(t == ')') {				// ако е затваряща скоба
			while(stack.pop(t2) && t2 != '(')	// докато от стека не изскочи отваряща скоба
				polish.push(t2);		// поставяме операторите в обр. полски запис
			if(t2 != '(') {				// ако в стека няма отваряща скоба
				cerr << "Има несъответствие на скобите в израза!\n";
				exit(1);
			}
		} else {
			cerr << "Невалиден символ: '" << t << "'!\n";
			exit(1);калкулатор с
		}
	}

	// тук символите от входния израз са свършили
	while(stack.pop(t)) {					// докато има символ в стека
		if(t == '(') {					// ако той е скоба
			cerr << "Има несъответствие на скобите в израза!\n";
			exit(1);
		}
		polish.push(t);					// добавяме оператора в обр. полски запис
	}

	cout << "Полският запис: " << polish << endl;
	return polish;
}

double calculate(Queue<char> polish) {				// изчислява обратния полски запис и връща стойността
	Stack<double> stack;
	char t1;

	while(polish.pop(t1)) {					// докато има символ в обр. полски запис
		if(priority(t1) == NUMBER) {			// ако той е операнд
			stack.push(t1 - 48);			// го конвертираме до число и го поставяме в стека
		} else {					// ако е оператор
			double n1, n2;
			if( !(stack.pop(n1) && stack.pop(n2)) ) {	// вземаме двете операнди на върха на стека
				cerr << "Грешка: Некоректни входни данни!\n";	// ако няма две операнди
				exit(1);
			}
			stack.push(oper(t1, n2, n1));		// прилагаме оператора t1 вурху тях
								// и поставяме резултата отново в стека
		}
	}

	double res;
	stack.pop(res);						// вземаме резултата, който е останал в стека
	if(stack.pop(res)) {					// ако във стека има повече елементи
		cerr << "Грешка: Некоректни входни данни!\n";
		exit(1);
	}
	return res;						// връщаме резултата
}

Queue<char> read() {						// прочита аритметичен израз и го връща
	cout << "Въведете аритметичния израз: ";
	Queue<char> expr;					// в тази опашка ще съхраняваме аритметичния израз
	char ch;
	while(true) {
		cin.get(ch);					// четем символи от клавиатурата
		if (ch == '\n') return expr;			// до срещането на нов ред
		if(ch != ' ')					// игнорираме интервалите
			expr.push(ch);
	}
}
