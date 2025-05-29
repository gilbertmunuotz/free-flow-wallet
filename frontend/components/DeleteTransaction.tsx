'use client';

import { useState, useTransition } from "react";
import { Trash } from "lucide-react";
import { toast } from "sonner";
import { Dialog, DialogTrigger, DialogContent, DialogHeader, DialogFooter, DialogTitle, DialogDescription } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { DeleteP2P } from "@/app/p2p/_action";
import { useRouter } from "next/navigation";

export default function DeleteTransaction({ txId }: { txId: string }) {

    const router = useRouter();

    const [open, setOpen] = useState(false);
    const [isPending, startTransition] = useTransition();

    const handleDelete = () => {
        startTransition(async () => {
            try {
                const formData = new FormData();
                formData.set("id", txId);
                await DeleteP2P(formData);
                router.refresh();
                toast.success("Deleted successfully!");
                setOpen(false); // close dialog on success
            } catch (error) {
                toast.error("Failed to delete");
                console.error(error);
            }
        });
    };

    return (
        <Dialog open={open} onOpenChange={setOpen}>
            <DialogTrigger asChild>
                <button>
                    <Trash size={20} className="cursor-pointer text-red-600" />
                </button>
            </DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Confirm Delete</DialogTitle>
                    <DialogDescription>
                        Are you sure you want to delete this transaction? This action cannot be undone.
                    </DialogDescription>
                </DialogHeader>
                <DialogFooter className="flex justify-end gap-2">
                    <Button variant="outline" onClick={() => setOpen(false)}>Cancel</Button>
                    <Button
                        variant="destructive"
                        onClick={handleDelete}
                        disabled={isPending}
                    >
                        {isPending ? "Deleting..." : "Delete"}
                    </Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    );
}