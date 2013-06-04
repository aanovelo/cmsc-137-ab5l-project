<?php 

class Question  
{  
   public $questionId;
   public $text;
   public $image;
   public $choice1;
   public $choice2;
   public $choice3;
   public $choice4;
   public $answer;
         
}

class Quiz
{
   public $quizId;
   public $title;
   public $description;
   public $numItems;
}

class QuizSession
{
   public $sessionId;
   public $result;
   public $num_items;
   
   public function start_session()
   {
   
   }
   
   public function end_session()
   {
   
   }
   
   public function taker_name()
   {
   
   }
   
   public function randomize()
   {
   
   }
}
  
$obj = new Question;  

var_dump($obj);
  


?>