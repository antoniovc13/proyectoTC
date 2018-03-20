package com.tivit.talmatc.feature.traslado_carga.flight_list;

/**
 * Created by Antonio.Valdivieso on 20/03/2018.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
