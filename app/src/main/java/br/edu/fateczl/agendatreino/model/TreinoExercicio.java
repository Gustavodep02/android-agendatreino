package br.edu.fateczl.agendatreino.model;

public class TreinoExercicio {

    private int id;
    private Treino treino;
    private Exercicio exercicio;
    private int series;
    private int reps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return
                exercicio.getNome() +" - "+ exercicio.getDescricao() +
                ", series: " + series +
                ", reps: " + reps;
    }
}
