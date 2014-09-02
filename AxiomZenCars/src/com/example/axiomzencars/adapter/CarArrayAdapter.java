package com.example.axiomzencars.adapter;

import java.util.List;

import com.example.axiomzencars.R;
import com.example.axiomzencars.data.car.Car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CarArrayAdapter extends ArrayAdapter<Car> {

    private Context context;
    private List<Car> cars;

    public CarArrayAdapter(Context context, List<Car> cars) {
        super(context, R.layout.list_item_car, cars);
        this.context = context;
        this.cars = cars;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Car car = cars.get(position);

        View rowView = inflater.inflate(R.layout.list_item_car, parent, false);

        TextView carMakeTextView = (TextView) rowView.findViewById(R.id.car_make_text_view);
        TextView carModelTextView = (TextView) rowView.findViewById(R.id.car_model_text_view);
        TextView carYearTextView = (TextView) rowView.findViewById(R.id.car_year_text_view);
        TextView carPriceTextView = (TextView) rowView.findViewById(R.id.car_price_text_view);

        carMakeTextView.setText(car.getModelMake().getMake());
        carModelTextView.setText(car.getModelMake().getModel());
        carYearTextView.setText(String.format("%d", car.getYear().value()));
        carPriceTextView.setText(String.format("%d", car.getPrice().value()));

        return rowView;
    }
}
