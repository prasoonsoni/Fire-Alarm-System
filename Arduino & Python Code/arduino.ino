int REDLED=7;
int BUZZER = 6;
int GREENLED = 8;
int temperature = A4;


void setup() {
  // put your setup code here, to run once:
  pinMode(GREENLED, OUTPUT);
  pinMode(REDLED, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  Serial.begin(9600);

}

void loop() {
  int gas = analogRead(A5);
  int val = analogRead(A4);
  float mv = ( val/1024.0)*5000;
  float fahrenheit = mv/10;
  float cel = (fahrenheit-32)*5/9;
  if(gas>450){
    digitalWrite(REDLED, HIGH);
    digitalWrite(GREENLED, LOW);
    digitalWrite(BUZZER, HIGH);
    Serial.print("True");
    Serial.print(" ");
    Serial.print(gas);
    Serial.print(" ");
    Serial.print(cel);
    Serial.println();
  } else {
    digitalWrite(REDLED, LOW);
    digitalWrite(GREENLED, HIGH);
    digitalWrite(BUZZER, LOW);
    Serial.print("False");
    Serial.print(" ");
    Serial.print(gas);
    Serial.print(" ");
    Serial.print(cel);
    Serial.println();
  }
  delay(3000);
}